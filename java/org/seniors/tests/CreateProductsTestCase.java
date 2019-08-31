package org.server.tests;

import java.math.BigDecimal;
import java.net.InetAddress;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.server.model.Category;
import org.server.model.Offer;

public class CreateProductsTestCase {

	public static void main(String[] args) {
		
		String ipAddress = "";
		System.out.println(System.getProperty("user.dir"));
		try {
			
			InetAddress inetAddress = InetAddress.getLocalHost();
			ipAddress = inetAddress.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Category restaurante = new Category();
		Category bares  = new Category();
		Category cinema = new Category();
		Category teatro = new Category();
		Category viagem = new Category();

		restaurante.setName("Restaurante");
		bares.setName("Bares");
		cinema.setName("Cinema");
		teatro.setName("Teatro");
		viagem.setName("Viagem");
		
		
		
		
		Offer jantar = new Offer();
		jantar.setValue(new BigDecimal("150"));
		jantar.setPoints(50);
		jantar.setDescription("<b>Jantar do L�o</b>: "
				+ "Jantar para dois, no melhor da Cidade. "
				+ "Desfrute de  nossas �timas carnes, Voc� ir� se apaixonar!");
		jantar.addImageURL("http://"+ipAddress+":8080/server/banners/banner1_offers_screen.png");
		jantar.setCategory(restaurante);
		
		
		Offer hotel = new Offer();
		hotel.setValue(new BigDecimal("374"));
		hotel.setPoints(35);
		hotel.setDescription("<b>Hotel do Lima</b>: "
				+ "Aproveite um dia maravilhoso em nosso hotel,"
				+ "com piscina e �rea de lazer para as crian�as!");
		hotel.addImageURL("http://"+ipAddress+":8080/server/banners/banner2_offers_screen.png");
		hotel.setCategory(viagem);
		
		Offer jantarDoLeo = new Offer();
		jantarDoLeo.setValue(new BigDecimal("97"));
		jantarDoLeo.setPoints(40);
		jantarDoLeo.setDescription("<b>Jantar do L�o</b>: "
				+ "Jantar para dois, no melhor da Cidade. "
				+ "Desfrute de  nossas �timas carnes, Voc� ir� se apaixonar!");
		jantarDoLeo.addImageURL("http://"+ipAddress+":8080/server/banners/banner3_offers_screen.png");
		jantarDoLeo.setCategory(restaurante);
		
		Offer massagem = new Offer();
		massagem.setValue(new BigDecimal("25"));
		massagem.setPoints(15);
		massagem.setDescription("<b>Jantar do L�o</b>: "
				+ "Jantar para dois, no melhor da Cidade. "
				+ "Desfrute de  nossas �timas carnes, Voc� ir� se apaixonar!");
		massagem.addImageURL("http://"+ipAddress+":8080/server/banners/banner5_offers_screen.png");
		massagem.setCategory(viagem);
		
		Offer sushi = new Offer();
		sushi.setValue(new BigDecimal("35"));
		sushi.setPoints(30);
		sushi.setDescription("<b>Jantar do L�o</b>: "
				+ "Jantar para dois, no melhor da Cidade. "
				+ "Desfrute de  nossas �timas carnes, Voc� ir� se apaixonar!");
		sushi.addImageURL("http://"+ipAddress+":8080/server/banners/banner6_offers_screen.png");
		sushi.setCategory(restaurante);
		
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("seniors-web");
		EntityManager em = emf.createEntityManager();;
		
		em.getTransaction().begin();
		
		em.persist(bares); 
		em.persist(cinema); 
		em.persist(teatro); 
		em.persist(viagem);
		
		em.persist(jantar);
		em.persist(hotel);
		em.persist(jantarDoLeo);
		em.persist(massagem);
		em.persist(sushi);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}