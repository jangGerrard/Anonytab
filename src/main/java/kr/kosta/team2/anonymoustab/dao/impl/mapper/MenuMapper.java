package kr.kosta.team2.anonymoustab.dao.impl.mapper;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Menu;

public interface MenuMapper {
	
	public Menu selectMenu(long code);
	
	public List<Menu> selectMenus();
}
