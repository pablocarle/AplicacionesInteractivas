package com.parking.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.parking.app.db.AbonosMapper;
import com.parking.app.db.AutosMapper;
import com.parking.app.db.BancosMapper;
import com.parking.app.db.ClienteMapper;
import com.parking.app.db.ContratosMapper;
import com.parking.app.db.MediosDePagoMapper;
import com.parking.app.model.Abono;
import com.parking.app.model.AbonoView;
import com.parking.app.model.Auto;
import com.parking.app.model.AutoView;
import com.parking.app.model.Banco;
import com.parking.app.model.BancoView;
import com.parking.app.model.ChequeView;
import com.parking.app.model.Cliente;
import com.parking.app.model.ClienteView;
import com.parking.app.model.Cochera;
import com.parking.app.model.CocheraEstandar;
import com.parking.app.model.Contrato;
import com.parking.app.model.ContratoView;
import com.parking.app.model.MapaCocheras;
import com.parking.app.model.MedioPago;
import com.parking.app.model.MedioPagoView;
import com.parking.app.model.Tarifa;

public class SistemaCocheras {
	
	public static int MEDIOPAGO_EFECTIVO = -1;
	public static int MEDIOPAGO_CHEQUE = -2;
	public static int ABONO_SINABONO = -1;
	
	private static SistemaCocheras instancia;
	
	private static Properties props;
	
	private static double PRECIOHORA;
	private static int CANTIDADCOCHERAS;
	
	static {
		Properties props = new Properties();
		SistemaCocheras.props = props;
		try {
			props.load(new FileInputStream(new File("app.properties")));
			PRECIOHORA = Double.parseDouble(props.getProperty("precioHora"));
			CANTIDADCOCHERAS = Integer.parseInt(props.getProperty("cantidadCocheras"));
		} catch (Exception e) {
			System.out.println("Error cargando archivo de propiedades del sistema");
			e.printStackTrace();
		}
	}
	
	private boolean inicializado = false;
	
	private SistemaCocheras() {
		super();
	}
	
	private void cargarModelo() throws Exception {
		clientes = ClienteMapper.obtenerMapper().selectAll();
		bancos = BancosMapper.obtenerMapper().selectAll();
		mediosPago = MediosDePagoMapper.obtenerMapper().selectAll();
		autos = AutosMapper.obtenerMapper().selectAll();
		contratos = ContratosMapper.obtenerMapper().selectAll();
		abonos = AbonosMapper.obtenerMapper().selectAll();
	}

	public static SistemaCocheras getSistemaCocheras() {
		if (instancia == null) {
			instancia = new SistemaCocheras();
		}
		return instancia;
	}
	
	private Collection<Cliente> clientes = new ArrayList<Cliente>();
	private Collection<Contrato> contratos = new ArrayList<Contrato>();
	private Collection<Cochera> cocheras = new ArrayList<Cochera>();
	private Collection<Tarifa> tarifas = new ArrayList<Tarifa>();
	private Collection<Abono> abonos = new ArrayList<Abono>();
	private Collection<Banco> bancos = new ArrayList<Banco>();
	private Collection<Auto> autos = new ArrayList<Auto>();
	private Collection<MedioPago> mediosPago = new ArrayList<MedioPago>();
	private MapaCocheras mapaCocheras = new MapaCocheras();
	
	{
		try {
			cargarModelo();
		} catch (Exception e) {
			System.out.println("error en carga de modelo. " + e);
			e.printStackTrace();
		}
	}
	
	public String getSistemaCocherasProperty(String propertyName) {
		return props.getProperty(propertyName);
	}
	
	public ClienteView crearCliente(String nombre, String domicilio, String email, String telefono) throws Exception {
		if (!existeCliente(email)) {
			Cliente nuevoCliente = new Cliente();
			nuevoCliente.setActivo(true);
			nuevoCliente.setEmail(email);
			nuevoCliente.setDomicilio(domicilio);
			nuevoCliente.setNombre(nombre);
			nuevoCliente.setTelefono(telefono);
			
			int idCliente = ClienteMapper.obtenerMapper().insert(nuevoCliente);
			nuevoCliente.setIdCliente(idCliente);
			clientes.add(nuevoCliente);
			return nuevoCliente.obtenerVista();
		} else {
			return null;
		}
	}
	
	private boolean existeCliente(String email) {
		for (Cliente cliente : clientes) {
			if (cliente.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public ClienteView bajaCliente(int idCliente) throws Exception {
		Cliente cliente = obtenerCliente(idCliente);
		if (cliente == null)
			throw new Exception("No se encontro cliente con id " + idCliente);
		clientes.remove(cliente);
		ClienteMapper.obtenerMapper().delete(cliente);
		return cliente.obtenerVista();
	}

	public ClienteView buscarCliente(int idCliente) throws Exception {
		Cliente cliente = obtenerCliente(idCliente);
		if (cliente == null) {
			throw new Exception("No se encontro cliente con id " + idCliente);
		} else {
			return cliente.obtenerVista();
		}
	}
	
	public Vector<ClienteView> listarClientes() {
		Vector<ClienteView> lista = new Vector<ClienteView>();
		for (Cliente cliente : clientes) {
			if (cliente.isActivo()){
				lista.add(cliente.obtenerVista());	
			}
			
		}
		return lista;
	}
	
	public ClienteView asociarAuto(int idCliente, String patente, String modelo, String marca, boolean esGrande) throws Exception {
		Cliente cliente = obtenerCliente(idCliente);
		if (cliente != null) {
		    if(!patenteExistente(patente)) {
    		    Auto auto = new Auto(patente, marca, modelo, esGrande);
    		    int idAuto = AutosMapper.obtenerMapper().insert(auto);
    		    AutosMapper.obtenerMapper().asociarAutoCliente(idAuto, idCliente);
    		    auto.setIdAuto(idAuto);
    			cliente.asociarAuto(auto);
    			autos.add(auto);
    			ClienteView clienteView = cliente.obtenerVista();
    			return clienteView;
		    } else {
		        throw new Exception("Ya existe un auto con la patente " + patente);
		    }
		} else {
			throw new Exception("No se encontro cliente con id " + idCliente);
		}
	}
	
	private boolean patenteExistente(String patente) {
        for(Auto auto : autos) {
            if (auto.getPatente().equals(patente)) {
                return true;
            }
        }
        return false;
    }

    public Banco obtenerBanco(int idBanco) {
        for (Banco banco : bancos) {
            if (banco.getIdBanco() == idBanco) {
                return banco;
            }
        }
        return null;
    }

    private Cliente obtenerCliente(int idCliente) {
		for (Cliente cliente : clientes) {
			if (cliente.getIdCliente() == idCliente) {
				return cliente;
			}
		}
		return null;
	}

	public ContratoView crearContrato(int idCliente, String patente, int idMedioPago, int idAbono, List<ChequeView> cheques) throws Exception {
		if (!inicializado) {
			throw new Exception("Las cocheras no estan inicializadas. Inicialice layout de cocheras para comenzar");
		}
		Cliente cliente = obtenerCliente(idCliente);
		Auto auto = cliente.obtenerAuto(patente);
		MedioPago medioPago = obtenerMedioPago(idMedioPago);
		Abono abono = obtenerAbono(idAbono);
		if (mapaCocheras.hayDisponible(auto)) {
			
			
			
			Contrato contrato = new Contrato();
			contrato.setAuto(auto);
			contrato.setCliente(cliente);
			contrato.setMedioPago(medioPago);
			contrato.setAbono(abono);
			contrato.setIdContrato(ContratosMapper.obtenerMapper().insert(contrato));
			contratos.add(contrato);
			mapaCocheras.asignar(auto);
			return contrato.obtenerVista();
		} else {
			throw new Exception();
		}
	}

    private Abono obtenerAbono(int idAbono) throws Exception {
    	for (Abono abono : abonos) {
    		if (abono.getIdAbono() == idAbono) {
    			return abono;
    		}
    	}
    	throw new Exception("No se encontro el abono con id " + idAbono);
	}

	private MedioPago obtenerMedioPago(int idMedioPago) throws Exception {
		for (MedioPago mp : mediosPago) {
			if (mp.getIdMedioPago() == idMedioPago) {
				return mp;
			}
		}
		throw new Exception("No existe el medio de pago con ID " + idMedioPago);
	}

	public ClienteView modificarCliente(int idCliente, String nombre, String domicilio, String email, String telefono) throws Exception {
            Cliente cliente = obtenerCliente(idCliente);
            cliente.setActivo(true);
            cliente.setEmail(email);
            cliente.setNombre(nombre);
            cliente.setDomicilio(domicilio);
            cliente.setTelefono(telefono);
            ClienteMapper.obtenerMapper().update(cliente);
            return cliente.obtenerVista();
    }

	public void crearCocheras(int cocherasSimples, int cocherasEspeciales) throws Exception {
		if (!inicializado) {
			List<Cochera> cocheras = new ArrayList<Cochera>();
			int total = cocherasSimples + (cocherasEspeciales * 2);
			if (total == CANTIDADCOCHERAS) {
				Cochera cochera = null;
				int idsCount = 0;
				while (idsCount++ < total) {
					cochera = new CocheraEstandar();
					cochera.setIdCochera(idsCount);
					cocheras.add(cochera);
				}
				this.cocheras = mapaCocheras.inicializar(cocheras, cocherasSimples, cocherasEspeciales);
			} else {
				throw new Exception("La cantidad total debe ser igual a " + CANTIDADCOCHERAS);
			}
			inicializado = true;
		}
	}
	
	public boolean estanCocherasInicializadas() {
		return inicializado;
	}
	
	public MedioPagoView crearMedioPago(String nombre, Integer idBanco,
	        String descripcion) throws Exception {
        if(!existeMedioPago(nombre)) {
            Banco banco = null;
        
        	if (idBanco != null) {
        		banco = obtenerBanco(idBanco);
        	}
    		MedioPago nuevoMedioPago = new MedioPago(nombre, banco, descripcion);
    		int idMedioPago = MediosDePagoMapper.obtenerMapper().insert(nuevoMedioPago);
    		nuevoMedioPago.setIdMedioPago(idMedioPago);
    		mediosPago.add(nuevoMedioPago);
    		return nuevoMedioPago.obtenerVista();
        	
        }
        return null;
    }

	private boolean existeBanco(String nombre) {
		for (Banco banco : bancos) {
		    if (banco.getNombre().equals(nombre)) {
		        return true;
		    }
		}
		return false;
	}

	private boolean existeMedioPago(String nombre) {
        for (MedioPago medioPago : mediosPago) {
            if (medioPago.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }
    public AbonoView crearAbono(String nombre, int dias, float descuento) throws Exception {
    	if (!existeAbono(dias)) {
    		Abono abono = new Abono(nombre, dias, descuento);
    		int idAbono = AbonosMapper.obtenerMapper().insert(abono); 
    		abono.setIdAbono(idAbono);
    		abonos.add(abono);
    		return abono.obtenerVista();
    	} else {
    		throw new Exception("Ya existe un abono para " + dias + " dias");
    	}
    }
	private boolean existeAbono(int dias) {
		for (Abono abono : abonos) {
			if (abono.getDias() == dias) {
				return true;
			}
		}
		return false;
	}

	public Vector<MedioPagoView> listarMediosPago() {
		Vector<MedioPagoView> retList = new Vector<MedioPagoView>();
		for (MedioPago medio : mediosPago) {
			retList.add(medio.obtenerVista());
		}
		return retList;
	}

	public Vector<AbonoView> listarAbonos() {
		Vector<AbonoView> retList = new Vector<AbonoView>();
		for (Abono abono : abonos) {
			retList.add(abono.obtenerVista());
		}
		return retList;
	}

    public Vector<BancoView> listarBancos() {
        Vector<BancoView> retList = new Vector<BancoView>();
        for (Banco banco: bancos) {
            retList.add(banco.obtenerVista());
        }
        return retList;
    }

    public Vector<AutoView> listarAutos() {
        Vector<AutoView> lista = new Vector<AutoView>();
        for (Auto auto: autos) {
            lista.add(auto.obtenerVista());  
        }

        return lista;
    }

    public BancoView crearBanco(String nombre, String ftpOut, String ftpIn) throws Exception {
        if (!existeBanco(nombre)) {
            Banco banco = new Banco(nombre, ftpOut, ftpOut);
            int idBanco = BancosMapper.obtenerMapper().insert(banco); 
            banco.setIdAbono(idBanco);
            bancos.add(banco);
            return banco.obtenerVista();
        }
        return null;
    }

	public void eliminarAbono(int idAbono) throws Exception {
		Abono abono = obtenerAbono(idAbono);
		abonos.remove(abono);
		AbonosMapper.obtenerMapper().delete(idAbono);
	}

    public void bajaMedioPago(int idMedioPago) throws Exception {
        MedioPago medioPago = obtenerMedioPago(idMedioPago);
        mediosPago.remove(medioPago);
        MediosDePagoMapper.obtenerMapper().delete(idMedioPago);
    }
}
