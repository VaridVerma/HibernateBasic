import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;


public class StudentMain {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure(); 
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session =  factory.openSession();
		
		Student student = new Student();
		student.setName("ABC");
		student.setRoll(780);
		student.setSubject("Java");
		
		Transaction transaction = session.beginTransaction();
		
		try{
			transaction.begin();
			session.save(student);
			transaction.commit();
		
			System.out.println("Record Added");
			
			Query query =session.createQuery("from Student");
			
			List<Student> slist = query.list();
			
			for(Student student2 : slist){
				System.out.println(student2.getRoll()+" "+student2.getName()+" "+student2.getSubject());
			}
		}
		catch(ConstraintViolationException e){
			transaction.rollback();
			System.out.println("Duplicate Values Entered");
		}
		catch (HibernateException e) {
			e.printStackTrace();
			//transaction.rollback();
			System.out.println("Some Error");
		}
		
	}

}
