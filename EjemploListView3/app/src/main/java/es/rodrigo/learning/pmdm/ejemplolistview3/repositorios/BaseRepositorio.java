package es.rodrigo.learning.pmdm.ejemplolistview3.repositorios;

import java.util.ArrayList;

public interface BaseRepositorio<T> {
    public Integer guardar(T obj);
    public Integer eliminar(T obj);
    public T recuperar(Integer id);
    public ArrayList<T> recuperarTodos();
}
