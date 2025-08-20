package controls;

import java.util.Map;

import bind.DataBinding;
import dao.BookDao;

public class BookSearchController implements Controller, DataBinding {
	BookDao bookdao;
	public BookSearchController setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"searchbook",String.class};
	}

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		String searchbook = (String) model.get("searchbook");
		if(searchbook != null && !searchbook.trim().isEmpty()) {
			model.put("book",bookdao.searchBook(searchbook));
		}else {
			model.put("book",bookdao.selectList());
		}
		return "/book/BookList.jsp";
	}

}
