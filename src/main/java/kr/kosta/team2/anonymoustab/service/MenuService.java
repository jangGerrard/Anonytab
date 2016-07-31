package kr.kosta.team2.anonymoustab.service;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Menu;

public interface MenuService {
	public Menu findMenu(long code);
	
	public List<Menu> findMenus();
}
