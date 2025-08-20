package controls;

import java.util.Map;

import dao.BookDao;

public class BookListController implements Controller {
	BookDao bookdao;
	public BookListController setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
		return this;
	}
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		model.put("book",bookdao.selectList());
		return "/book/BookList.jsp";
	}

}
