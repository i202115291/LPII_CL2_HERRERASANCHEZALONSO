package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Interfaces.IProducto;
import model.TblProductocl2;

public class ClassProducto implements IProducto{

	@Override
	public void RegistrarProducto(TblProductocl2 producto) {
		// TODO Auto-generated method stub
		EntityManagerFactory fabr=Persistence.createEntityManagerFactory("LP_CL2_HERRERASANCHEZALONSO");
	    //permite gestionar entidades
		EntityManager em=fabr.createEntityManager();
		//iniciar transaccion
		em.getTransaction().begin();
		//registramos
		em.persist(producto);
		//emitimos mensaje por consola
		System.out.println("Producto registrado en la BD correctamente");
		//confirmamos
		em.getTransaction().commit();
		//cerramos la transaccion
		em.close();
	}

	@Override
	public void ActualizarProducto(TblProductocl2 producto) {
		// TODO Auto-generated method stub
		EntityManagerFactory fabr=Persistence.createEntityManagerFactory("LP_CL2_HERRERASANCHEZALONSO");
		//permite gestionar entidades
		EntityManager em=fabr.createEntityManager();
		//iniciar transaccion
		em.getTransaction().begin();
		//actualizamos
		em.merge(producto);
		//comfirmamos 
		em.getTransaction().commit();
		//cerramos
		em.close();
	}
	
	@Override
	public TblProductocl2 BuscarProducto(TblProductocl2 producto) {
		//establecemos la conexion con la unidad de persistencia..
		EntityManagerFactory fabr=Persistence.createEntityManagerFactory("LP_CL2_HERRERASANCHEZALONSO");
		//gestionar las entidads
		EntityManager em=fabr.createEntityManager();
		//iniciamos la transaccion
		em.getTransaction().begin();
		//recuperamos el codigo a buscar
		TblProductocl2 busprod=em.find(TblProductocl2.class,producto.getIdproductocl2());
		//confirmamos 
		em.getTransaction().commit();
		//cerramos
		em.close();
		return busprod;
	}  //fin del metodo buscar cliente...

	@Override
	public List<TblProductocl2> ListadoProducto() {
		// TODO Auto-generated method stub
		//establecemos la conexion con la unidad de persistencia..
        EntityManagerFactory fabr=Persistence.createEntityManagerFactory("LP_CL2_HERRERASANCHEZALONSO");
        //gestionamos las entidads
        EntityManager em=fabr.createEntityManager();
        //iniciamos la transaccion
        em.getTransaction().begin();
        //recuperamos los productos de la base de datos
        //***********utilizando jpql
        List<TblProductocl2> listadoproducto=em.createQuery("select p from TblProductocl2 p",TblProductocl2.class).getResultList();
        //confirmamos la transaccion
        em.getTransaction().commit();
        //cerramos
        em.close();
		return listadoproducto;
	}

}
