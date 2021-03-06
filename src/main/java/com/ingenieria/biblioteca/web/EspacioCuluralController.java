/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingenieria.biblioteca.web;

import com.ingenieria.biblioteca.controlador.EspacioculturalJpaController;
import com.ingenieria.biblioteca.controlador.ProfesorJpaController;
import com.ingenieria.biblioteca.controlador.SalaculturalJpaController;
import com.ingenieria.biblioteca.modelo.Espaciocultural;
import com.ingenieria.biblioteca.modelo.PersistenceUtil;
import com.ingenieria.biblioteca.modelo.Profesor;
import com.ingenieria.biblioteca.modelo.Salacultural;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author alexis
 */
@ManagedBean
@RequestScoped
public class EspacioCuluralController {

    EspacioculturalJpaController jpaEspacio;
    ProfesorJpaController jpaProfesor;
    SalaculturalJpaController jpaSala;

    Salacultural sala;
    Espaciocultural espacio;
    Profesor profesor;
    
    
    List<Salacultural> salas;
    List<Espaciocultural> espacios;
    List<Profesor> profesores;
    
    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event;
    
    int tmpProf;
    int tmpSala;

    /**
     * Creates a new instance of EventoCuluralController
     */
    public EspacioCuluralController() {
        jpaEspacio = new EspacioculturalJpaController(PersistenceUtil.getEntityManagerFactory());
        espacio = new Espaciocultural();
        espacios = jpaEspacio.findEspacioculturalEntities();
        
        jpaProfesor = new ProfesorJpaController(PersistenceUtil.getEntityManagerFactory());
        profesor = new Profesor();
        profesores = jpaProfesor.findProfesorEntities();
        
        

        jpaSala = new SalaculturalJpaController(PersistenceUtil.getEntityManagerFactory());
        sala = new Salacultural();
        salas = jpaSala.findSalaculturalEntities();

        event = new DefaultScheduleEvent();

        lazyEventModel = new LazyScheduleModel();
      
        tmpProf = 0;
        tmpSala = 0;

    }

    
    public EspacioculturalJpaController getJpaEspacio() {
        return jpaEspacio;
    }

    public ProfesorJpaController getJpaProfesor() {
        return jpaProfesor;
    }

    public void setJpaProfesor(ProfesorJpaController jpaProfesor) {
        this.jpaProfesor = jpaProfesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void setJpaEspacio(EspacioculturalJpaController jpaEspacio) {
        this.jpaEspacio = jpaEspacio;
    }

    public SalaculturalJpaController getJpaSala() {
        return jpaSala;
    }

    public void setJpaSala(SalaculturalJpaController jpaSala) {
        this.jpaSala = jpaSala;
    }

    public Salacultural getSala() {
        return sala;
    }

    public void setSala(Salacultural sala) {
        this.sala = sala;
    }

    public Espaciocultural getEspacio() {
        return espacio;
    }

    public void setEspacio(Espaciocultural espacio) {
        this.espacio = espacio;
    }

    public List<Salacultural> getSalas() {
        return salas;
    }

    public void setSalas(List<Salacultural> salas) {
        this.salas = salas;
    }

    public List<Espaciocultural> getEspacios() {
        return espacios;
    }

    public void setEspacios(List<Espaciocultural> espacios) {
        this.espacios = espacios;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public int getTmpSala() {
        return tmpSala;
    }
    
    public int getTempProf(){
        return tmpProf;
        
    }
    
    public void setTmpProf(int tmpProf){
        this.tmpProf = tmpProf;
    }
    
    public void setTmpSala(int tmpSala) {
        this.tmpSala = tmpSala;
    }

    public void reserva() {
        if(existeSala(tmpSala)){
            if(!coincidenEventos()){
           
            espacio.setIdsala(jpaSala.findSalacultural(tmpSala));
            espacio.setIdprofesor(jpaProfesor.findProfesor(tmpProf));
            System.out.println("Estoy intentando guardar");
            jpaEspacio.guardar(espacio);
        }
        }
        espacios = jpaEspacio.findEspacioculturalEntities();
    }
    
       
    public List<Espaciocultural> coincidenFechas(){
        List<Espaciocultural> c = new ArrayList();
        for(Espaciocultural e: espacios){
            
                if (espacio.getFecha().equals(e.getFecha())) {
                    c.add(e);
                }
            
        }
        return c;
    }
    
    public boolean coincideHora(Espaciocultural e1, Espaciocultural e2){
        if(e1.getHorainicio().equals(e2.getHorainicio())){
            return true;
        }else if(e1.getHorafinal().equals(e2.getHorafinal())){
            return true;
        }else if(e1.getHorainicio().after(e2.getHorainicio()) && e1.getHorainicio().before(e2.getHorafinal())){
            return true;
        }else if(e1.getHorainicio().before(e2.getHorainicio()) && e1.getHorafinal().after(e2.getHorainicio())){
            return true;
        }else if(e1.getHorainicio().after(e2.getHorainicio()) && e1.getHorafinal().before(e2.getHorafinal())){
            return true;
        }else{
            return false;
        }
           

  
    }
    
    
    public boolean coincidenEventos(){
       List<Espaciocultural> c = coincidenFechas();
       for(Espaciocultural e: c){
           if(coincideHora(espacio,e))
               return true;
       }
       return false;
    }
    
    

    public boolean existeSala(int e) {
        for (Salacultural s : salas) {
            if (s.getIdsala() == (e)) {
                return true;
            }
        }
        return false;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addEvent() {
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
    }

    private void muestraMensaje(String mensaje) {
        FacesMessage mensajeFace = new FacesMessage(mensaje);
        RequestContext.getCurrentInstance().showMessageInDialog(mensajeFace);
    }

    public void modificar() {
        jpaEspacio.modificar(espacio);
        espacios = jpaEspacio.findEspacioculturalEntities();
    }

    public void eliminar() {
        jpaEspacio.eliminar(espacio);
        espacios = jpaEspacio.findEspacioculturalEntities();
    }

    public void activaEvento() {

        espacio = jpaEspacio.findEspaciocultural(espacio.getIdevento());

        espacio.setReservado(true);
        jpaEspacio.modificar(espacio);

    }

    public void desactivaEvento() {

        espacio = jpaEspacio.findEspaciocultural(espacio.getIdevento());

        espacio.setReservado(false);
        jpaEspacio.modificar(espacio);

    }

        
    public Espaciocultural buscar(){
	espacios.clear();
	espacios = jpaEspacio.findEspaciocultural(espacio);
        System.out.println(espacios);
        return jpaEspacio.findEspaciocultural(espacio.getIdevento());
    }
    
    public void muestraTodo(){
        espacios = jpaEspacio.findEspacioculturalEntities();
    }
    
 
    public List<Espaciocultural> listaUsuario(int id){
        List<Espaciocultural> tmp = jpaEspacio.findEspacioculturalEntities();
        for(Espaciocultural e : tmp){
            if(e.getIdprofesor().getIdprofesor() == id){
                tmp.add(e);
            }   
        }
        return tmp;
        
    }
    
    
}
