package controls;

import java.util.Map;

import bind.DataBinding;
import dao.BookDao;
import dto.Book;

public class BookUpdateController implements Controller, DataBinding {
	BookDao bookdao;
	public BookUpdateController setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"id",Integer.class,"book",dto.Book.class};
	}

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		Book book = (Book)model.get("book");
		if(book.getTitle() == null) {
			int id = (int) model.get("id");
			model.put("book", bookdao.selectOne(id));
			return "/book/BookUpdateForm.jsp";
		}else {
			bookdao.update(book);
			return "redirect:list.do";
		}
	}
}