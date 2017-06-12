package pl.edu.pjatk.tau.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.pjatk.tau.domain.Computer;
import pl.edu.pjatk.tau.domain.Shop;

@Component
@Transactional
public class ComputerShopImpl implements ComputerShop {
	
	 @Autowired
	 private SessionFactory sessionFactory;
	 
	  public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	@Override
	public void addShop(Shop shop) {
		shop.setId(null);
	        sessionFactory.getCurrentSession().save(shop);
	        sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public void editShop(Shop shop) {
		 sessionFactory.getCurrentSession().update(shop);
		
	}

	@Override
	public void deleteShop(Shop shop) {
		sessionFactory.getCurrentSession().delete(shop);
		
	}

	@Override
	public Shop findShopById(long id) {
		return (Shop) sessionFactory.getCurrentSession().getNamedQuery("Shop.byId").setLong("id", id).uniqueResult();
	}
	
	
	 public Shop findShopByName(String name){
		 return (Shop) sessionFactory.getCurrentSession().getNamedQuery("Shop.byName").setString("id", name).uniqueResult();
		 
	 }

	@SuppressWarnings("unchecked")
	@Override
	public List<Shop> getAllShops() {
		 return sessionFactory.getCurrentSession().getNamedQuery("Player.getAll").list();
	}

	@Override
	public Long addNewComputer(Computer comp) {
		comp.setId(null);
        return (Long) sessionFactory.getCurrentSession().save(comp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getAllComputers() {
		  return sessionFactory.getCurrentSession().getNamedQuery("Computer.findAll").list();
	}

	@Override
	public void updateComputer(Computer comp) {
		 sessionFactory.getCurrentSession().update(comp);		
	}

	@Override
	public void deleteComputer(Computer comp) {
		 sessionFactory.getCurrentSession().delete(comp);	
	}

	@Override
	public Computer getComputerById(int id) {
		 return (Computer) sessionFactory.getCurrentSession().getNamedQuery("Computer.byId").setInteger("id", id).uniqueResult();
	}

	@Override
	public Computer getComputerByMark(String mark) {
		 return (Computer) sessionFactory.getCurrentSession().getNamedQuery("Computer.byMark").setString("id", mark).uniqueResult();
	}

	@Override
	public Computer getComputerByPrice(int price) {
		 return (Computer) sessionFactory.getCurrentSession().getNamedQuery("Computer.byPrice").setInteger("id", price).uniqueResult();
	}

	@Override
	public void sellComputer(Long idShop, Long idComputer) {
		 Shop shop = sessionFactory.getCurrentSession().get(Shop.class, idShop);
		 Computer comp = sessionFactory.getCurrentSession().get(Computer.class, idComputer);
		 
		 boolean inStock = false;
		 for (Computer comps : shop.getComputers()) {
	            if (comps.getId() == (comp.getId())) {
	            	inStock = true;
	                break;
	            }
	        }
		 
		 if (inStock) 
		 {
			shop.increseRevenue(comp.getPrice());
	        shop.getComputers().remove(comp);     
	     } 
		 else System.out.println("Towaru nie ma w magazynie");
	        
	
	}

	
}
