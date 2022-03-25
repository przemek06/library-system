/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.config;

import com.mycompany.library.system.entity.User;
import com.mycompany.library.system.repository.BookRepository;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author swiat
 */
@Singleton
@Startup
public class StartupConfig {

    @PersistenceContext
    EntityManager em;

    @Inject
    BookRepository bookRepository;

    @PostConstruct
    public void startup() {
        String query
                = "insert into PERSISTENCE_BOOKS (ISBN, TITLE, AUTHOR, CATEGORY, PUBLISHED) values ('018358808-8','Charly','Duncan Larmour','Drama|Sci-Fi','1911-12-02 19:06:32'), \n"
                + "('252414388-0','Kidnapping Mr. Heineken','Caritta Fennell','Action|Crime|Drama|Thriller','1980-07-06 03:38:43'), \n"
                + "('898874711-9','Chato''s Land','Silvia McKissack','Western','2015-05-05 09:59:17'), \n"
                + "('435173937-1','Alphabet Killer, The','Averill Tatlock','Crime','1982-06-16 07:28:04'), \n"
                + "('918679497-3','Family Plot','Creighton Croston','Comedy|Thriller','1910-05-19 06:23:24'), \n"
                + "('078545148-X','Cliffhanger','Wernher Cristoforo','Action|Adventure|Thriller','1940-05-11 11:05:59'), \n"
                + "('924873383-2','Het Vonnis','Anastassia Ceely','Crime|Drama','1986-10-02 06:42:34'), \n"
                + "('496708000-6','Mum & Dad','Gerrie Masey','Horror','2014-02-13 14:23:46'), \n"
                + "('734439624-6','Every Other Week (Varannan vecka)','Mathilde Joontjes','Comedy|Drama|Romance','2020-06-03 12:14:33'), \n"
                + "('941144890-9','World Without End','Shoshanna Ditchfield','Sci-Fi','1956-03-27 23:23:35'), \n"
                + "('225259697-X','Hemingway & Gellhorn','Bordy Derisley','Drama|Romance|War','1904-09-09 10:51:26'), \n"
                + "('213466479-7','Brothers: The Return','Merry Vink','Crime|Drama|Thriller','1914-11-17 23:40:27'), \n"
                + "('776820230-9','Gunfight at the O.K. Corral','Luisa Caile','Western','1994-05-24 21:18:59'), \n"
                + "('278766236-6','Penda''s Fen','Agata Churchouse','Fantasy','1960-01-07 13:56:57'), \n"
                + "('252766624-8','Melody','Kassandra Haveline','Drama|Romance','1986-12-17 11:47:33'), \n"
                + "('559044210-9','Lady in Number 6: Music Saved My Life, The','Hastie Tieman','Documentary|Drama','1972-01-05 07:05:36'), \n"
                + "('436927945-3','Woman Next Door, The (Femme d''à côté, La)','Marquita Blaker','Drama|Romance','1916-01-19 16:33:58'), \n"
                + "('572919184-7','My Blue Heaven','Sherman Johananoff','Comedy','1952-08-22 18:55:58'), \n"
                + "('052095527-7','No Way Out','Adham Ingleton','Drama|Film-Noir','1916-10-17 10:09:15'), \n"
                + "('813349403-6','Murder by Contract','Rhianna Sawart','Crime|Drama|Film-Noir|Thriller','1914-04-20 03:56:12'), \n"
                + "('282180586-1','Pickle, The','Gabey Feake','Comedy','1969-08-17 15:20:51'), \n"
                + "('569309252-6','Bliss','Cindra Gotcliffe','Comedy|Drama','1982-11-01 17:12:19'), \n"
                + "('021044407-X','Shakespeare-Wallah','Pierce Apark','Drama','1914-09-05 02:48:53'), \n"
                + "('924210429-9','Wonderful World','Jammal Klimashevich','Drama|Romance','1929-08-01 22:21:24'), \n"
                + "('091002633-5','Snow and Ashes','Nixie Abrehart','Drama|Thriller|War','2001-10-21 14:49:48'), \n"
                + "('770315591-8','Xtro 3: Watch the Skies','Josephina Benfield','Horror|Sci-Fi','1931-06-05 02:02:01'), \n"
                + "('833425867-4','Picking Up the Pieces','Marisa Hallam','Comedy|Fantasy','1982-12-05 11:28:30'), \n"
                + "('854429436-7','Sherlock Holmes in Pearl of Death (Pearl of Death, The)','Sile Partener','Crime|Horror|Mystery|Thriller','1993-08-08 05:02:01'), \n"
                + "('748144967-8','Bride of the Monster','Israel Langfield','Horror|Sci-Fi','1935-04-13 06:59:50'), \n"
                + "('307341278-6','Beaver, The','Skipper Marthen','Drama','1926-02-10 11:52:16'), \n"
                + "('080465110-8','Invisible Waves','Ari McGeneay','Adventure|Crime|Drama','2013-12-16 12:16:39'), \n"
                + "('965215346-X','Muscle Shoals','Troy Dodgson','Documentary','2007-09-30 10:59:11'), \n"
                + "('199380660-1','Inheritance, The (Karami-ai)','Adara Bubbings','Drama','2017-05-27 11:13:11'), \n"
                + "('779118809-0','Election Day','Charmaine Dunklee','Documentary','1920-05-23 09:34:19'), \n"
                + "('660815238-8','No Escape','Granville Sybry','Action|Drama|Sci-Fi','1986-07-14 06:08:26'), \n"
                + "('761420826-9','The Devil Thumbs a Ride','Ulrikaumeko Mishow','Drama|Film-Noir','1961-09-07 11:29:17'), \n"
                + "('976203622-0','Mulberry Street','Gerik Narracott','Action|Horror|Thriller','1987-07-31 23:51:07'), \n"
                + "('492960251-3','Illegal','Zorine Towers','Crime|Drama|Film-Noir','1914-07-10 14:44:57'), \n"
                + "('489671159-9','The Mystery of the Leaping Fish','Nichole Butterworth','Comedy','1986-07-02 09:05:14'), \n"
                + "('089187810-6','Long Weekend','Berty Smuth','Horror|Mystery|Thriller','1911-04-29 21:01:18'), \n"
                + "('772923483-1','Mummy Returns, The','Shani Brugden','Action|Adventure|Comedy|Thriller','1978-09-14 11:21:19'), \n"
                + "('106525645-0','Fear X','Renelle Mix','Mystery|Thriller','1930-12-06 03:47:10'), \n"
                + "('684857097-4','Chinese Puzzle (Casse-tête chinois)','Mariele Overbury','Comedy|Romance','1978-06-21 12:58:17'), \n"
                + "('576681946-3','Hexed','Pippo Dudin','Comedy','1912-05-13 01:02:28'), \n"
                + "('912357850-5','20 Dates','Garry Grange','Comedy|Romance','1970-08-15 11:49:31'), \n"
                + "('643696068-6','Pronto','Dru MacNally','Comedy|Crime|Thriller','1934-09-19 06:06:36'), \n"
                + "('269636181-9','Pulse','Aluino Trowler','Action|Drama|Fantasy|Horror|Mystery|Sci-Fi|Thriller','1956-09-01 19:53:11'), \n"
                + "('102143284-9','Figures in a Landscape','Jasen Vitet','Thriller','2008-05-05 05:48:13'), \n"
                + "('263003240-X','McFarland USA','Lois Marquese','Drama','2003-12-12 04:03:21'), \n"
                + "('848447357-0','Coming Home (Gui lai)','Loralee Ferroni','Drama','2012-09-14 16:18:25'), \n"
                + "('820546228-3','Where the Heart Is','Trudi Hains','Comedy|Drama','1926-07-05 02:34:03'), \n"
                + "('644657543-2','Suur Tõll','Cherri Greenhaugh','(no genres listed)','1973-08-15 07:15:18'), \n"
                + "('679059016-1','Osaka Elegy (Naniwa erejî)','Jordan Greguoli','Drama','2010-05-26 05:58:06'), \n"
                + "('601190183-2','Brand Upon the Brain!','Kirby Laxton','Adventure|Fantasy|Mystery','1919-10-25 02:25:03'), \n"
                + "('714432772-0','Invincible Iron Man, The','Rosamund Dewdney','Animation','1978-04-03 07:00:56'), \n"
                + "('614606071-2','Great Gabbo, The','Timothea Bandy','Drama|Musical|Romance','1934-06-15 19:15:17'), \n"
                + "('038796167-4','Cinderfella','Madalyn Stother','Comedy','2006-11-19 06:51:10'), \n"
                + "('000215122-7','Dead in the Water','Colas Aleksic','Crime|Thriller','1935-12-22 07:30:44'), \n"
                + "('326948271-X','Boogie Woogie','Annnora Shreeves','Comedy|Drama','1964-10-09 07:53:44'), \n"
                + "('217772599-2','Traveler, The (Mossafer)','Hebert Matheson','Drama','1954-06-21 22:47:04'), \n"
                + "('994315914-6','Brothers Karamazov, The','Christa Rabbitt','Drama','1959-01-21 15:31:46'), \n"
                + "('434544155-2','Submarine X-1','Merrick Trittam','Drama|War','1964-07-13 22:38:36'), \n"
                + "('770361729-6','Talladega Nights: The Ballad of Ricky Bobby','Conn Thormann','Action|Comedy','1955-03-11 23:06:28'), \n"
                + "('495440911-X','Boo to You Too! Winnie the Pooh','Dolley Fears','(no genres listed)','1939-10-22 16:57:12'), \n"
                + "('857559307-2','The Left Handed Gun','Grissel Sywell','Crime|Drama|Western','1937-03-24 13:11:39'), \n"
                + "('528601722-3','Broadway Rhythm','Toma Jakoviljevic','Musical','1961-04-08 08:04:32'), \n"
                + "('069849401-6','Young Visiters, The','Mady Sturgess','Comedy|Romance','2002-02-15 00:42:24'), \n"
                + "('830590570-5','Luv','Millisent Kelsey','Comedy','1937-09-03 17:09:31'), \n"
                + "('213475142-8','This Above All','Zelda Drewell','Drama|Romance|War','2003-02-21 10:04:16'), \n"
                + "('296022700-X','Souper, Le (Supper, The)','Hansiain Bracher','Drama','1981-05-17 01:24:24'), \n"
                + "('393139311-9','Dead Weekend','Nonna Klimecki','Comedy|Sci-Fi','2014-01-28 17:20:13'), \n"
                + "('192789030-6','Trials of Oscar Wilde, The','Wini Bartleet','Drama','1969-08-02 16:32:51'), \n"
                + "('051355222-7','41','Lyndsie Burberry','Documentary','1907-11-10 00:44:00'), \n"
                + "('037945390-8','The Frame','Adelice Routh','Crime|Drama|Fantasy|Romance|Thriller','1990-05-11 01:45:56'), \n"
                + "('858906710-6','V.I.P.s, The','Maribelle Anderson','Drama','1969-08-22 03:19:49'), \n"
                + "('663671965-6','Where Are the Dreams of Youth? (Seishun no yume imaizuko)','Justen Deinert','Drama','1911-10-08 00:05:52'), \n"
                + "('641597835-7','Tarzan the Ape Man','Giustina Spilsted','Action|Adventure','1965-11-28 18:50:32'), \n"
                + "('055987982-2','Einstein and Eddington','Newton Kelley','Drama','2014-08-01 16:41:12'), \n"
                + "('438961922-5','Speed Of Thought, The','Norrie Kiessel','Sci-Fi|Thriller','1925-04-07 10:32:11'), \n"
                + "('022780443-0','Days Between, The (In den Tag hinein)','Helyn Scotter','Drama','1912-02-26 05:53:46'), \n"
                + "('100210285-5','Quiller Memorandum, The','Liva Shotboult','Action|Drama|Mystery|Thriller','2003-09-23 19:32:41'), \n"
                + "('419899648-2','U2: Rattle and Hum','Raul Chanson','Documentary|Musical','1905-10-04 04:49:39'), \n"
                + "('017174480-2','Wipers Times, The','Emyle Howse','War','1924-11-30 01:56:14'), \n"
                + "('765092432-7','Presumed Innocent','Esta Footitt','Crime|Drama|Thriller','1971-07-15 02:56:43'), \n"
                + "('600284079-6','City of Pirates (La ville des pirates) ','Bev Loram','Drama|Fantasy','1918-08-07 01:55:42'), \n"
                + "('194775361-4','Circle of Iron','Eal McSwan','Action|Adventure|Fantasy|Mystery','1946-07-01 05:53:44'), \n"
                + "('330012975-3','Pretty One, The','Karon Normington','Comedy|Drama','1927-12-12 17:38:21'), \n"
                + "('632804882-3','Satan Bug, The','Joey Pretsel','Sci-Fi|Thriller','1973-05-26 13:12:36'), \n"
                + "('846985742-8','Impromptu','Mirna Tees','Comedy|Romance','1946-08-18 19:13:16'), \n"
                + "('060177439-6','Who Am I? (Wo shi shei)','Dicky Laye','Action|Adventure|Comedy|Sci-Fi|Thriller','1993-09-15 01:21:12'), \n"
                + "('010412354-0','Blue Swallow (Cheong yeon)','Irita Goad','Drama|Romance','1964-05-04 10:40:01'), \n"
                + "('217753290-6','Thr3e (Three)','Elwood Leatham','Drama|Horror|Mystery|Thriller','1973-08-31 15:24:52'), \n"
                + "('282726400-5','Fog, The','Arlena Bagshawe','Horror','1928-06-15 03:12:32'), \n"
                + "('483206963-2','Interceptor','Nelie Levicount','Action|Sci-Fi','2002-03-18 03:43:27'), \n"
                + "('721390524-4','Double Trouble','Tallie Mayhou','Action|Comedy','2020-06-22 18:26:24'), \n"
                + "('365316151-7','Kitty Foyle','Tracee Rattrie','Drama|Romance','1958-01-05 08:11:35'), \n"
                + "('942417215-X','Executive Decision','Jarrett Andreolli','Action|Adventure|Thriller','1970-04-19 07:39:21'), \n"
                + "('009927093-5','The Heavy','Saudra Marchant','Action|Thriller','2000-03-29 15:44:41'), \n"
                + "('119032350-8','Rouva presidentti','Skylar De Miranda','Documentary','1975-07-20 18:38:11'), \n"
                + "('181382522-X','Union, The','Nikolaos Bowfin','Documentary','1992-08-07 20:33:05')";

        em.persist(new User("admin", "pass", "admin@gmail.com", "ADMIN"));
        em.persist(new User("user", "pass", "user@gmail.com", "USER"));

        try {
            em.createNativeQuery(query).executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR 1 " + ex.getMessage());
        }

    }
}
