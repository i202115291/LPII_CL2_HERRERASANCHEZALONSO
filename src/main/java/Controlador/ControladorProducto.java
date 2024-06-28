package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ClassProducto;
import model.TblProductocl2;

/**
 * Servlet implementation class ControladorProducto
 */
public class ControladorProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		TblProductocl2 producto = new TblProductocl2();
	    ClassProducto crud = new ClassProducto();
	    List<TblProductocl2> listadoproducto = crud.ListadoProducto();
	    request.setAttribute("listadodeproductos", listadoproducto);

	    String accion = request.getParameter("accion");
	    if (accion != null) {
	        switch (accion) {
	            case "Actualizar":
	                int id = Integer.parseInt(request.getParameter("id"));
	                producto.setIdproductocl2(id);
	                TblProductocl2 buscarid = crud.BuscarProducto(producto);
	                request.setAttribute("ID", buscarid.getIdproductocl2());
	                request.setAttribute("nombre", buscarid.getNombrecl2());
	                request.setAttribute("preciocomp", buscarid.getPreciocompcl2());
	                request.setAttribute("precioven", buscarid.getPrecioventacl2());
	                request.setAttribute("estado", buscarid.getEstadocl2());
	                request.setAttribute("descrip", buscarid.getDescripcl2());
	                break;
	        }
	    }

	    // Siempre redirige a RegistrarProducto.jsp
	    request.getRequestDispatcher("RegistrarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String nombre = request.getParameter("nombre");
        double preciocomp;
        double precioven;
        String estado = request.getParameter("estado");
        String descrip = request.getParameter("descrip");

        try {
            preciocomp = Double.parseDouble(request.getParameter("preciocomp"));
            precioven = Double.parseDouble(request.getParameter("precioven"));
        } catch (NumberFormatException e) {
            // Manejo de errores para precios no v�lidos
            request.setAttribute("mensajeRegistro", "Error: Precio no v�lido.");
            request.getRequestDispatcher("RegistrarProducto.jsp").forward(request, response);
            return;
        }

        TblProductocl2 producto = new TblProductocl2();
        ClassProducto crud = new ClassProducto();
        
        producto.setNombrecl2(nombre);
        producto.setPreciocompcl2(preciocomp);
        producto.setPrecioventacl2(precioven);
        producto.setEstadocl2(estado);
        producto.setDescripcl2(descrip);
        
        crud.RegistrarProducto(producto);

        // Redirige a s� mismo para evitar reenv�os del formulario al recargar
        response.sendRedirect("ControladorProducto");
    }
}