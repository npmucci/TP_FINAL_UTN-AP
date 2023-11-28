package com.Grupo_2_Java_Intermedio.Manger;

import com.Grupo_2_Java_Intermedio.Entidades.*;
import com.Grupo_2_Java_Intermedio.Enumeradores.EstadoEnum;
import com.Grupo_2_Java_Intermedio.Enumeradores.MedioEnum;
import com.Grupo_2_Java_Intermedio.services.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@NoArgsConstructor
@Transactional
public class DataManager {

    private ClienteService clienteService;
    private  EspecialidadService especialidadService;
    private  IncidenteService incidenteService;
    private  MedioComunicacionService medioComunicacionService;
    private  ServicioService servicioService;
    private TecnicoService tecnicoService;
    private  TipoProblemaService tipoProblemaService;

    @Autowired
    public void DataManager(
            ClienteService clienteService,
            EspecialidadService especialidadService,
            IncidenteService incidenteService,
            MedioComunicacionService medioComunicacionService,
            ServicioService servicioService,
            TecnicoService tecnicoService,
            TipoProblemaService tipoProblemaService) {
        this.clienteService = clienteService;
        this.especialidadService = especialidadService;
        this.incidenteService = incidenteService;
        this.medioComunicacionService = medioComunicacionService;
        this.servicioService = servicioService;
        this.tecnicoService = tecnicoService;
        this.tipoProblemaService = tipoProblemaService;
    }

    public void inicializarDatos() {
        crearServicios();
        crearEspecialidades();
        crearTiposProblema();
        crearClientes();
        crearTecnicos();
        crearIncidentes();
    }

    public void crearIncidentes() {
        List<Incidente> incidentes = new ArrayList<>();

        Cliente cliente1 = clienteService.buscarPorId(1);
        Cliente cliente2 = clienteService.buscarPorId(2);
        Cliente cliente3 = clienteService.buscarPorId(3);

        Tecnico tecnico1 = tecnicoService.buscarPorId(1);
        Tecnico tecnico2 = tecnicoService.buscarPorId(2);
        Tecnico tecnico3 = tecnicoService.buscarPorId(3);

        TipoProblema tipoProblema1 = tipoProblemaService.buscarPorId(1);
        TipoProblema tipoProblema2 = tipoProblemaService.buscarPorId(2);
        TipoProblema tipoProblema3 = tipoProblemaService.buscarPorId(3);

        Servicio servicio1 = servicioService.buscarPorId(1);
        Servicio servicio2 = servicioService.buscarPorId(2);
        Servicio servicio3 = servicioService.buscarPorId(3);

        Especialidad especialidad1 = especialidadService.buscarPorId(1);
        Especialidad especialidad2 = especialidadService.buscarPorId(2);
        Especialidad especialidad3 = especialidadService.buscarPorId(3);

        // Crear incidentes ficticios
        Incidente incidente1 = new Incidente();
        incidente1.setTitulo("Incidente 1");
        incidente1.setDescripcion("Descripción incidente 1");
        incidente1.setFechaIngreso((new GregorianCalendar(2023, Calendar.NOVEMBER, 10)).getTime());
        incidente1.setFechaEstimadaResolucion((new GregorianCalendar(2023, Calendar.NOVEMBER, 30)).getTime());
        incidente1.setEstado(EstadoEnum.FINALIZADO);
        incidente1.setFechaResolucion((new GregorianCalendar(2023, Calendar.NOVEMBER, 27)).getTime());
        incidente1.setCliente(cliente1);
        incidente1.setTecnico(tecnico1);
        incidente1.setTiposProblema(Collections.singletonList(tipoProblema1));
        incidente1.setServicio(servicio1);
        incidente1.setEspecialidades(Collections.singletonList(especialidad1));

        Incidente incidente2 = new Incidente();
        incidente2.setTitulo("Incidente 2");
        incidente2.setDescripcion("Descripción incidente 2");
        incidente2.setFechaIngreso((new GregorianCalendar(2023, Calendar.OCTOBER, 1)).getTime());
        incidente2.setFechaEstimadaResolucion((new GregorianCalendar(2023, Calendar.NOVEMBER, 30)).getTime());
        incidente2.setEstado(EstadoEnum.FINALIZADO);
        incidente2.setFechaResolucion((new GregorianCalendar(2023, Calendar.NOVEMBER, 27)).getTime());
        incidente2.setCliente(cliente2);
        incidente2.setTecnico(tecnico2);
        incidente2.setTiposProblema(Collections.singletonList(tipoProblema2));
        incidente2.setServicio(servicio2);
        incidente2.setEspecialidades(Collections.singletonList(especialidad2));

        Incidente incidente3 = new Incidente();
        incidente3.setTitulo("Incidente 3");
        incidente3.setDescripcion("Descripción incidente 3");
        incidente3.setFechaIngreso((new GregorianCalendar(2023, Calendar.NOVEMBER, 1)).getTime());
        incidente3.setFechaEstimadaResolucion((new GregorianCalendar(2023, Calendar.NOVEMBER, 30)).getTime());
        incidente3.setEstado(EstadoEnum.FINALIZADO);
        incidente3.setFechaResolucion(((new GregorianCalendar(2023, Calendar.NOVEMBER, 27)).getTime()));
        incidente3.setCliente(cliente3);
        incidente3.setTecnico(tecnico3);
        incidente3.setTiposProblema(Collections.singletonList(tipoProblema3));
        incidente3.setServicio(servicio3);
        incidente3.setEspecialidades(Collections.singletonList(especialidad3));

        // Guardar incidentes en la base de datos
        incidenteService.guardar(incidente1);
        incidenteService.guardar(incidente2);
        incidenteService.guardar(incidente3);
    }

    public void crearTecnicos() {
        Tecnico t1 = new Tecnico();
        t1.setNombre("Juan");
        t1.setApellido("De la Mar");
        t1.setEspecialidad(especialidadService.buscarTodos());
        List<MedioComunicacion> mediosTecnico1 = new ArrayList<>();
        
        MedioComunicacion mc11 = new MedioComunicacion(1, MedioEnum.EMAIL, "jmar@gmail.com");
        MedioComunicacion mc12 = new MedioComunicacion(2, MedioEnum.WHATSAPP, "1152222222");
        medioComunicacionService.guardar(mc11);
        mediosTecnico1.add(mc11);
        medioComunicacionService.guardar(mc12);
        mediosTecnico1.add(mc12);
        
        t1.setMediosComunicacion(mediosTecnico1);
        tecnicoService.guardar(t1);

        Tecnico t2 = new Tecnico();
        t2.setNombre("Paco");
        t2.setApellido("De la Mar");
        t2.setEspecialidad(especialidadService.buscarTodos());
        List<MedioComunicacion> mediosTecnico2 = new ArrayList<>();

        MedioComunicacion mc21 = new MedioComunicacion(1, MedioEnum.EMAIL, "pamar@gmail.com");
        MedioComunicacion mc22 = new MedioComunicacion(2, MedioEnum.WHATSAPP, "1153333333");
        medioComunicacionService.guardar(mc21);
        mediosTecnico2.add(mc21);
        medioComunicacionService.guardar(mc22);
        mediosTecnico2.add(mc22);

        t2.setMediosComunicacion(mediosTecnico2);
        tecnicoService.guardar(t2);

        Tecnico t3 = new Tecnico();
        t3.setNombre("Pedro");
        t3.setApellido("De la Mar");
        t3.setEspecialidad(especialidadService.buscarTodos());
        List<MedioComunicacion> mediosTecnico3 = new ArrayList<>();

        MedioComunicacion mc31 = new MedioComunicacion(1, MedioEnum.EMAIL, "pemar@gmail.com");
        MedioComunicacion mc32 = new MedioComunicacion(2, MedioEnum.WHATSAPP, "1153333333");
        medioComunicacionService.guardar(mc31);
        mediosTecnico3.add(mc31);
        medioComunicacionService.guardar(mc32);
        mediosTecnico3.add(mc32);

        t3.setMediosComunicacion(mediosTecnico3);
        tecnicoService.guardar(t3);

    }

    public void crearTiposProblema() {
        TipoProblema p1 = new TipoProblema(1,"tipo1",3,5,especialidadService.buscarTodos());
        TipoProblema p2 = new TipoProblema(2,"tipo2",3,5,especialidadService.buscarTodos());
        TipoProblema p3 = new TipoProblema(3,"tipo3",3,5,especialidadService.buscarTodos());
        p1.setId(tipoProblemaService.guardar(p1));
        p2.setId(tipoProblemaService.guardar(p2));
        p3.setId(tipoProblemaService.guardar(p3));
    }


    public void crearEspecialidades() {
        Especialidad especialidad1 = new Especialidad(1,"especialidad1","Descripcion1");
        Especialidad especialidad2 = new Especialidad(2,"especialidad2","Descripcion1");
        Especialidad especialidad3 = new Especialidad(3,"especialidad3","Descripcion1");
        especialidad1.setId(especialidadService.guardar(especialidad1));
        especialidad2.setId(especialidadService.guardar(especialidad2));
        especialidad3.setId(especialidadService.guardar(especialidad3));
    }

    public void crearServicios() {
        Servicio servicio1 = new Servicio(1,"servicio1","Descripcion1");
        Servicio servicio2 = new Servicio(2,"servicio2","Descripcion2");
        Servicio servicio3 = new Servicio(3,"servicio3","Descripcion3");
        servicio1.setId(servicioService.guardar(servicio1));
        servicio2.setId(servicioService.guardar(servicio2));
        servicio3.setId(servicioService.guardar(servicio3));
    }

    public void crearClientes() {
        Cliente cliente1 = new Cliente(1,2011111118,"Alejandro Lamas","aLamas@gmail.com","Alejandro","Lamas", servicioService.buscarTodos());
        Cliente cliente2 = new Cliente(2,2022222229,"Miguel Lardo","mLardo@gmail.com","Miguel","Lardo", servicioService.buscarTodos());;
        Cliente cliente3 = new Cliente(3,273333335,"Natalia Mucci","nMucci@gmail.com","Natalia","Mucci", servicioService.buscarTodos());
        cliente1.setId(clienteService.guardar(cliente1));
        cliente2.setId(clienteService.guardar(cliente2));
        cliente3.setId(clienteService.guardar(cliente3));

    }
}
