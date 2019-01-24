package com.test.pojo;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.test.pojo.Employee;
import com.test.util.HibernateUtil;

public class UserDAO {
	
			public static void main(String[] args) {

			//SessionFactory sf = HibernateUtil.getSessionFactory();
//			readFromDatabase(sf);
//			readFromDatabaseSortedOrder(sf);
//			update(sf);
//			delete(sf);
			persistUsingHibernate();
			}
			
			public static void persistUsingHibernate() {
			       Session session = HibernateUtil.getSessionFactory().openSession();
			        
			        session.beginTransaction();
			        Employee employee = new Employee();
			        
			        employee.setDeptId(1);
//			        employee.setId(15);
			        employee.setLastName("Palmer");
			        employee.setDepartment("Advisor");
			        employee.setEmail("rpalmer@abc.com");
			        
			        
			        session.save(employee);
			        session.getTransaction().commit();
			}

		public static void update(SessionFactory sf) {
			
			Session session = sf.openSession();		
			session.getTransaction().begin();
			
			String hql = "update Employee set title= :title where id= :id";
			
			Query updateQuery = session.createQuery(hql);
			updateQuery.setParameter("title", "Senior Manager");
			updateQuery.setParameter("id", 5);

			int count = updateQuery.executeUpdate();
			System.out.println("count: " + count);
			
			session.getTransaction().commit();
			session.close();
			
		}
		
		public static void delete(SessionFactory sf) {
			
			Session session = sf.openSession();		
			session.getTransaction().begin();
			
			String hql = "delete Employee where id= :id";
			
			Query updateQuery = session.createQuery(hql);
			updateQuery.setParameter("id", 7);

			int count = updateQuery.executeUpdate();
			System.out.println("count: " + count);
			
			session.getTransaction().commit();
			session.close();
			
		}
		
		public static void readFromDatabase(SessionFactory sf) {
			
			Session session = sf.openSession();		
			String hql = "from Employee where id= :value";
			Query readQuery = session.createQuery(hql);
			readQuery.setParameter("value", 14);
			
			List list = readQuery.list();
			Employee employee = (Employee)list.get(0);
			
			System.out.println("Emp Name:" + employee.getLastName());
			
			session.close();
			
		}
		
		public static void readFromDatabaseSortedOrder(SessionFactory sf) {
			
			Session session = sf.openSession();		
			String hql = "from Employee order by yearsOfExperience desc";
			Query readQuery = session.createQuery(hql);
			
			List list = readQuery.list();
			
			for(Object obj: list) {
				
				Employee emp = (Employee)obj;
				System.out.println("Emp Name:" + emp.getLastName());
			}
			session.close();
		}

	}


