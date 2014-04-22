package com.is.buyabike.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.is.buyabike.dao.CategoryDao;
import com.is.buyabike.dao.ProductDao;
import com.is.buyabike.dao.SupplierDao;
import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.Supplier;
import com.is.buyabike.domain.client.Client;
import com.is.buyabike.domain.order.Order;
import com.is.buyabike.domain.order.Order.OrderStatus;

@Component
public class StartupBean {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ClientService clientService;

	@PostConstruct
	public void init() {
		Category stadsfietsen = new Category("Stadsfietsen", "Praktisch, stevig, meestal met een dichte kettingkast en met 0 tot 8 versnellingen. Dat kenmerkt een stadsfiets. Altijd startklaar voor gebruik. Helemaal van nu zijn damesfietsen en herenfietsen met een voordrager: de transportfietsen. Maar er zijn ook stadsfietsen met meer oog voor luxe en comfort. Welke fiets past bij u?");
		Category elektrischeFietsen = new Category("Elektrische fietsen","Een elektrische fiets of e-bike is een fiets met trapondersteuning. U fietst zelf, maar hebt het gevoel dat u altijd een duwtje in de rug krijgt!" );
		Category racefietsen = new Category("Racefietsen", "Een racefiets is licht, rijdt soepel en is uitgerust met veel versnellingen. Dé ingrediënten om goed aan je conditie te werken. En dat maakt niet uit of je beginner bent en kiest voor een instapmodel of als veeleisende fietser een hightech racefiets neemt. Geen zin om alleen te fietsen?");
		Category mountainbikes = new Category("Mountainbikes", "Midden in de natuur off-road aan je conditie werken. Dat kan op een mountainbike of ATB. Trotseer zandpaden, modder, heuvels en boomstronken. Als beginnende mountainbiker kies je voor een opstapmodel. Wil je meer? Neem dan een high-tech ATB");

		Product merida1 = new Product("Merida Big Nine TFS500", "De Big Nine 500 is perfect voor de startende off-road rijder. De 29 inch banden zorgen voor extra comfort en stabiliteit. Fietsen over hobbels en door modder gaat namelijk veel gemakkelijker. Ook vanwege de grootte van de banden worden trillingen makkelijker opgevangen.", "test", 700.00,
				999.00, 20);
		Product merida2 = new Product("Merida Big Nine XT Edition", "De Big Nine XT Edition is door zijn veelzijdigheid perfect geschikt als allround sportfiets. Door de 29 inch banden fiets u comfortabeler en stabieler dan de normale 26 inch banden.", "test", 1000.00,
				1399.00, 30);
		Product merida3 = new Product("Merida Matts 10", "Een echte hardtail mountainbike die doet wat er van hem verwacht wordt. Ideaal voor de beginnende fietser!", "test", 200.00,
				389.00, 20);
		
		Product giant1 = new Product("Giant Defy 5 2014 Grijs Claris 24", "Deze soepele, superlichte endurance racefiets is ontwikkeld in samenwerking met de Giants-profs en is geschikt voor lange toeren en dagelijkse ritten. De input van de Giant wielerprofs zorgde ervoor dat de fiets zeer geschikt is voor de lange, zware klassiekers en extreme toertochten. Het frame is voorzien van rondere buisvormen om de verticale doorbuiging te vergroten en de materiaalvermoeiing te verkleinen.","test",500,649.00,20);
		Product giant2 = new Product("Giant Defy 1 2014 Zwart-rood 105 30", "Deze soepele, superlichte endurance racefiets is ontwikkeld in samenwerking met de Giants-profs en is geschikt voor lange toeren en dagelijkse ritten. De input van de Giant wielerprofs zorgde ervoor dat de fiets zeer geschikt is voor de lange, zware klassiekers en extreme toertochten. Het frame is voorzien van rondere buisvormen om de verticale doorbuiging te vergroten en de materiaalvermoeiing te verkleinen.","test",1000,1199.00,20);
		Product giant3 = new Product("Giant Avail Composite 2 2014 105 30", "Snelheid en wendbaarheid, plus een soepel en comfortabel rijgevoel. Van sprints tot extreme toertochten, dit is jouw wapen. Het frame is efficient voor klimmen en sprinten zonder verlies aan comfort. Door toepassing van de Liv giant 3F-ontwerpfilosofie, is de fiets voorzien van een vrouwspecifieke endurance-geometrie.","test",1200,1699.00,20);
		
		Product raleigh1 = new Product("Raleigh Transporter", "Robuuste transporter, met 3 versnellingen!","test",400,489.00,20);
		Product gazelle1 = new Product("Gazelle Orange Plus", "De Orange Plus is een compleet uitgeruste stadsfiets, waarbij comfort voorop staat. U kunt soepel fietsen met een ontspannen zithouding dankzij de speciale framegeometrie. Is daarnaast zeer onderhoudsarm door de fraaie, gesloten FlowLine kettingkast.","test",700,799.00,20);
				
		
		
		Supplier raleigh = new Supplier("Raleigh", new Address(
				"Street", "2", "City", "State", "Country"), "http://www.raleigh.nl/", "phone");
		Supplier giant = new Supplier("Giant",new Address(
				"Street", "2", "City", "State", "Country"), "http://www.giant.nl/", "phone" );
		Supplier merida = new Supplier ("Merida",new Address(
				"Street", "2", "City", "State", "Country"), "http://www.merida.nl/", "phone" );
		Supplier gazelle = new Supplier ("Gazelle",new Address(
				"Street", "2", "City", "State", "Country"), "http://www.Gazelle.nl/", "phone" );

		supplierDao.persist(raleigh);
		supplierDao.persist(giant);
		supplierDao.persist(merida);
		supplierDao.persist(gazelle);
		
		
		//Mountainbikes
		
		
		merida1.setSupplier(merida);
		merida2.setSupplier(merida);
		merida3.setSupplier(merida);

		productDao.persist(merida1);
		productDao.persist(merida2);
		productDao.persist(merida3);
		
		mountainbikes.addProduct(merida1);
		mountainbikes.addProduct(merida2);
		mountainbikes.addProduct(merida3);


		categoryDao.persist(mountainbikes);
		
		//racefiesten
		
		giant1.setSupplier(giant);
		giant2.setSupplier(giant);
		giant3.setSupplier(giant);
		
		productDao.persist(giant1);
		productDao.persist(giant2);
		productDao.persist(giant3);
		
		racefietsen.addProduct(giant1);
		racefietsen.addProduct(giant2);
		racefietsen.addProduct(giant3);		
		
		categoryDao.persist(racefietsen);
		
		//stadsfietsen
		gazelle1.setSupplier(gazelle);
		raleigh1.setSupplier(raleigh);
		
		productDao.persist(gazelle1);
		productDao.persist(raleigh1);
		
		stadsfietsen.addProduct(gazelle1);
		stadsfietsen.addProduct(raleigh1);
		
		categoryDao.persist(stadsfietsen);
		
		

		Address address = new Address("krommeweg", "123", "Zuid-Laren", "Utrecht", "Nederland");
		Client client = new Client("Berend", "Botje", "berend@botje.nl", address, "pw");
		clientService.create(client);
		
		Order order = new Order(client);
		order.addProduct(raleigh1);
		order.addProduct(raleigh1);
		order.addProduct(raleigh1);
		order.addProduct(gazelle1);
		order.setStatus(OrderStatus.RECEIVED);

		orderService.persist(order);

		Order order2 = new Order(client);
		order2.addProduct(merida1);
		order2.addProduct(merida1);
		order2.addProduct(merida2);
		order2.addProduct(merida2);
		order2.setStatus(OrderStatus.RECEIVED);

		orderService.persist(order2);
	}
}
