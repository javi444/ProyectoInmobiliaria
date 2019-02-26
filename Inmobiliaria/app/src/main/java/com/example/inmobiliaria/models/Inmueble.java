package com.example.inmobiliaria.models;

public class Inmueble {

    private String id;
    private String titulo;
    private String descripcion;
    private double precio;
    private String habitaciones;
    private double tamanyo;
    private String categoria;
    private String direccion;
    private String codigo;
    private String ciudad;
    private String provincia;
    private String loc;

    public Inmueble() {
    }

    public Inmueble(String id, String titulo, String descripcion, double precio, String habitaciones, double tamanyo, String categoria, String direccion, String codigo, String ciudad, String provincia, String loc) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.habitaciones = habitaciones;
        this.tamanyo = tamanyo;
        this.categoria = categoria;
        this.direccion = direccion;
        this.codigo = codigo;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.loc = loc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(String habitaciones) {
        this.habitaciones = habitaciones;
    }

    public double getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(double tamanyo) {
        this.tamanyo = tamanyo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inmueble inmueble = (Inmueble) o;

        if (Double.compare(inmueble.precio, precio) != 0) return false;
        if (Double.compare(inmueble.tamanyo, tamanyo) != 0) return false;
        if (id != null ? !id.equals(inmueble.id) : inmueble.id != null) return false;
        if (titulo != null ? !titulo.equals(inmueble.titulo) : inmueble.titulo != null)
            return false;
        if (descripcion != null ? !descripcion.equals(inmueble.descripcion) : inmueble.descripcion != null)
            return false;
        if (habitaciones != null ? !habitaciones.equals(inmueble.habitaciones) : inmueble.habitaciones != null)
            return false;
        if (categoria != null ? !categoria.equals(inmueble.categoria) : inmueble.categoria != null)
            return false;
        if (direccion != null ? !direccion.equals(inmueble.direccion) : inmueble.direccion != null)
            return false;
        if (codigo != null ? !codigo.equals(inmueble.codigo) : inmueble.codigo != null)
            return false;
        if (ciudad != null ? !ciudad.equals(inmueble.ciudad) : inmueble.ciudad != null)
            return false;
        if (provincia != null ? !provincia.equals(inmueble.provincia) : inmueble.provincia != null)
            return false;
        return loc != null ? loc.equals(inmueble.loc) : inmueble.loc == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        temp = Double.doubleToLongBits(precio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (habitaciones != null ? habitaciones.hashCode() : 0);
        temp = Double.doubleToLongBits(tamanyo);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (codigo != null ? codigo.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (provincia != null ? provincia.hashCode() : 0);
        result = 31 * result + (loc != null ? loc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", habitaciones='" + habitaciones + '\'' +
                ", tamanyo=" + tamanyo +
                ", categoria='" + categoria + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigo='" + codigo + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
