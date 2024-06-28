package TestEntidades;

import java.util.List;

import Dao.ClassProducto;
import model.TblProductocl2;

public class TestEntidadProducto {
	public static void main(String[] args) {
		
		TblProductocl2 producto=new TblProductocl2();
		ClassProducto crud=new ClassProducto();
		//asignamos valores
		/*producto.setDescripcl2("Smartphone con pantalla de 6.5 pulgadas, 128GB de almacenamiento, cámara de 48MP");
		producto.setEstadocl2("Disponible");
		producto.setNombrecl2("Smartphone XYZ");
		producto.setPreciocompcl2(200.00);
		producto.setPrecioventacl2(250.00);
	    
		//invocamos al metodo registrar...
		crud.RegistrarProducto(producto);
		*/
		//testeamos el metodo listado
		List<TblProductocl2> listado=crud.ListadoProducto();
		//aplicamos un bucle for...
		for(TblProductocl2 lis:listado){
			
			//imprimimos por pantalla
			System.out.println("Id: "+lis.getIdproductocl2()+
					" Descripcion: "+lis.getDescripcl2() +" Estado: "+lis.getEstadocl2()
					+" Nombre: "+lis.getNombrecl2()+" Precio de Compra: "+lis.getPreciocompcl2()+
					" Precio de Venta: "+lis.getPrecioventacl2());
		}
	}
}
