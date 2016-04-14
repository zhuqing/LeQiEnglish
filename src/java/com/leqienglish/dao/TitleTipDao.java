package com.leqienglish.dao;

import java.util.List;

import com.leqienglish.entity.TitleTip;

public interface TitleTipDao {
	public boolean save(TitleTip titleTip);
	public List<TitleTip> getTitleTips();
}
