package com.parcial.blockbuster;

public interface Stock <T> {

    Boolean agregarAlStock(T con);

    Boolean agregarEjemplarAlStock(T con, Integer cant);

    Boolean decrementarEjemplarDelStock(T con);

    Boolean quitarDelStock(T con);
}
