package es.rodrigo.learning.pmdm.ejbbddyapplication.repositorios;

import java.util.List;

public interface BaseRepositorio<T> {
    Integer guardar(T obj);
    Integer eliminar(T obj);
    T recuperar(Integer id);
    List<T> recuperarTodos();
}
