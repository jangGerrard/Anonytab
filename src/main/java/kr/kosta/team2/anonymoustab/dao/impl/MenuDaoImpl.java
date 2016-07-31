package kr.kosta.team2.anonymoustab.dao.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.MenuDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.MenuMapper;
import kr.kosta.team2.anonymoustab.domain.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDaoImpl implements MenuDao{

	@Autowired
	private MenuMapper menuMapper;
	
	public Menu selectMenu(long code) {
		return this.menuMapper.selectMenu(code);
	}

	public List<Menu> selectMenus() {
		return this.menuMapper.selectMenus();
	}

}
