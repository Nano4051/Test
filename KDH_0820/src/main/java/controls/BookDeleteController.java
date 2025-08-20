package controls;

import java.util.Map;

import bind.DataBinding;
import dao.BookDao;

public class BookDeleteController implements Controller, DataBinding {
	BookDao bookdao;
	public BookDeleteController setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
		return this;
	}
	@Override	
	public Object[] getDataBinders() {
		return new Object[] {"id",Integer.class};
	}

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		if(model.get("book") == null) {
			int id = (int) model.get("id");
			model.put("book", bookdao.delete(id));
		}
		return "redirect:list.do";
	}
}