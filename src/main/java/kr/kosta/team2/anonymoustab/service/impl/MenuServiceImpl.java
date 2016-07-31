package kr.kosta.team2.anonymoustab.service.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.MenuDao;
import kr.kosta.team2.anonymoustab.domain.Menu;
import kr.kosta.team2.anonymoustab.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDao menuDao;

	public Menu findMenu(long code) {
		return this.menuDao.selectMenu(code);
	}

	public List<Menu> findMenus() {
		return this.menuDao.selectMenus();
	}
	
	
}
