package kr.kosta.team2.anonymoustab.dao;


import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Menu;

public interface MenuDao {
	public Menu selectMenu(long id);
	
	public List<Menu> selectMenus();
}
