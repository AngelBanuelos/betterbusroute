/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.search.Searching;

/**
 *
 * @author angel_banuelos
 */
@Entity
@Table(name = "route")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findById", query = "SELECT r FROM Route r WHERE r.id = :id"),
    @NamedQuery(name = "Route.findByRoute", query = "SELECT r FROM Route r WHERE r.route = :route"),
    @NamedQuery(name = "Route.findByName", query = "SELECT r FROM Route r WHERE r.name = :name"),
    @NamedQuery(name = "Route.findByPath1", query = "SELECT r FROM Route r WHERE r.path1 = :path1"),
    @NamedQuery(name = "Route.findByPath2", query = "SELECT r FROM Route r WHERE r.path2 = :path2")})
public class Route extends DomainEntity implements Searching, Comparable<Route> {

    @Lob
    @Column(name = "pathbyte1")
    private byte[] pathbyte1;
    @Lob
    @Column(name = "pathbyte2")
    private byte[] pathbyte2;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "route")
    private String route;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "path1")
    private String path1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "path2")
    private String path2;
    @OneToMany(mappedBy = "routeid")
    private Collection<Schedule> scheduleCollection;
    @OneToMany(mappedBy = "routeid")
    private Collection<History> historyCollection;

    @Transient
    private ArrayList<Coordinate> pathDecode1;
    @Transient
    private ArrayList<Coordinate> pathDecode2;

    @Transient
    private Route parent;

    @Transient
    private Coordinate intersection; //Posible la creacion de un Objecto Complejo que incluya el Path de la intersecion

    public Route() {
    }

    public Route(Integer id) {
        this.id = id;
    }

    public Route(Integer id, String route, String name, String path1, String path2) {
        this.id = id;
        this.route = route;
        this.name = name;
        this.path1 = path1;
        this.path2 = path2;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public ArrayList<Coordinate> getPathDecode1() {
        return pathDecode1;
    }

    public void setPathDecode1(ArrayList<Coordinate> pathDecode1) {
        this.pathDecode1 = pathDecode1;
    }

    public ArrayList<Coordinate> getPathDecode2() {
        return pathDecode2;
    }

    public void setPathDecode2(ArrayList<Coordinate> pathDecode2) {
        this.pathDecode2 = pathDecode2;
    }

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @XmlTransient
    public Collection<History> getHistoryCollection() {
        return historyCollection;
    }

    public void setHistoryCollection(Collection<History> historyCollection) {
        this.historyCollection = historyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.getIndex()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "masterdegree.ada.finalproject.model.Route[ id=" + id + " ]";
    }

    @Override
    public int getIndex() {
        return id;
    }

    @Override
    public int getValue() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public byte[] getPathbyte1() {
        return pathbyte1;
    }

    public void setPathbyte1(byte[] pathbyte1) {
        this.pathbyte1 = pathbyte1;
    }

    public byte[] getPathbyte2() {
        return pathbyte2;
    }

    public void setPathbyte2(byte[] pathbyte2) {
        this.pathbyte2 = pathbyte2;
    }

    public Route getParent() {
        return parent;
    }

    public void setParent(Route parent) {
        this.parent = parent;
    }

    public Coordinate getIntersection() {
        return intersection;
    }

    public void setIntersection(Coordinate intersection) {
        this.intersection = intersection;
    }

    @Override
    public int compareTo(Route o) {
//        if (this.id < o.getIndex()) {
//            return -1; // If it is lower than return -1
//        }
//        if (this.id > o.getIndex()) {
//            return 1; // If it is greter than return 1
//        }
//        return 0; // if are equals return 0
        return Integer.compare(this.id, o.getIndex());
    }

}
