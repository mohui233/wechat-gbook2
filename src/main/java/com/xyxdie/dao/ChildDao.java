package com.xyxdie.dao;

import java.util.List;

import com.xyxdie.model.Child;
import com.xyxdie.vo.ChildJsonBean;

public interface ChildDao {
    List<Child> findChildsByUserId(int id);

    Child findChildById(int id);

    List<ChildJsonBean> findAllChild(int pid);

    void saveChild(Child child);

    void deleteChild(Child child);

    Long findChildCount();

    List<ChildJsonBean> findChildByPage(final int pageNo,final int pageSize );
}
