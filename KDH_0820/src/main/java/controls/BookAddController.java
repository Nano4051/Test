package controls;

import java.util.Map;

import bind.DataBinding;
import dao.BookDao;
import dto.Book;

public class BookAddController implements Controller, DataBinding {
	BookDao bookdao;
	public BookAddController setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"book",dto.Book.class};
	}
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		Book book = (Book) model.get("book");
		if(book.getTitle() == null) {
			return "/book/BookAddForm.jsp";
		}else {
			bookdao.insert(book);
			return "redirect:list.do";
		}
	}

}
