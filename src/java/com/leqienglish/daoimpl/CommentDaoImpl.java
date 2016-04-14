package com.leqienglish.daoimpl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.leqienglish.dao.CommentDao;
import com.leqienglish.entity.Comment;
import com.leqienglish.entity.Content;
import com.leqienglish.entity.PageParam;

public class CommentDaoImpl<T extends Content> extends TampletDaoImpl<T>  implements CommentDao<T> {

    @Override
    public Class getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	

	

	
}
