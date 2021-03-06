package com.xyxdie.dao.imp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.xyxdie.dao.MessageDao;
import com.xyxdie.model.Message;
import com.xyxdie.vo.MessageJsonBean;

import java.util.List;

@Repository
public class MessageDaoImp extends BaseDaoImp<Message> implements MessageDao {

    public List<Message> findMessagesByUserId(int id){
        String hql = "from Message m where m.userid = "+ id +
                "Order by m.id desc";
        //return find(hql, id);
        return find(hql);
    }

    public Message findMessageById(int id){
        return get(Message.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<MessageJsonBean> findMessageJsonBeanById(int id){
        String hql = "select new com.xyxdie.vo.MessageJsonBean(" +
                "u.id, u.name, m.ip, m.date, m.message, u.imgUrl, u.type) " +
                "from User u, Message m where m.userid = u.id AND m.id = "+ id;
        List<MessageJsonBean> list = (List<MessageJsonBean>) getHibernateTemplate().find(hql);
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<MessageJsonBean> findAllMessage(){
        String hql = "select new com.xyxdie.vo.MessageJsonBean(" +
                "u.id, u.name, m.ip, m.date, m.message, u.imgUrl, m.status) " +
                "from User u, Message m where m.userid = u.id Order by m.id desc";
        List<MessageJsonBean> list = (List<MessageJsonBean>) getHibernateTemplate().find(hql);
        return list;
    }

    public int saveMessage(Message message){
		return save(message);
    }

    public void deleteMessage(Message message){
        delete(message);
    }

    public Long findMessageCount(){
        String hql = "select count(*) from Message as message";
        return (Long)getHibernateTemplate().find(hql).listIterator().next();
    }
    
    public Long findMsingleCount(int userid){
        String hql = "select count(*) from Message as m where m.userid = "+ userid;
        return (Long)getHibernateTemplate().find(hql).listIterator().next();
    }

    @SuppressWarnings("unchecked")
    public List<MessageJsonBean> findMessageByPage(final int pageNo,final int pageSize ){

        final String hql = "select new com.xyxdie.vo.MessageJsonBean(" +
                "m.id, u.name, m.ip, m.date, m.message, u.imgUrl, m.status) " +
                "from User u, Message m where m.userid = u.id Order by m.id desc";

        List<MessageJsonBean> list = (List<MessageJsonBean>) getHibernateTemplate().execute(new HibernateCallback<List<MessageJsonBean>>() {

            public List<MessageJsonBean> doInHibernate(Session session) throws HibernateException {
                List<MessageJsonBean> result = session.createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
                return result;
            }
        });
        return list;
    }

	@Override
	public List<MessageJsonBean> findMessageBySingle(final int pageNo, final int pageSize, int userid) {
        final String hql = "select new com.xyxdie.vo.MessageJsonBean(" +
                "m.id, u.name, m.ip, m.date, m.message, u.imgUrl, m.status) " +
                "from User u, Message m where m.userid = u.id  and m.userid = "+ userid +
                " Order by m.id desc";

        List<MessageJsonBean> list = (List<MessageJsonBean>) getHibernateTemplate().execute(new HibernateCallback<List<MessageJsonBean>>() {

            @SuppressWarnings("unchecked")
            public List<MessageJsonBean> doInHibernate(Session session) throws HibernateException {
                List<MessageJsonBean> result = session.createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
                return result;
            }
        });
        return list;
	}
}
