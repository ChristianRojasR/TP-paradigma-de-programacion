package Pruebas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Clases.Atraccion;
import Clases.Paquete;
import Clases.Visitante;
import ClasesPromocion.Absoluta;
import ClasesPromocion.Promocion;

public class Pruebas_Visitantes {

	@Test
	public void totalizador() {
		Atraccion minas = new Atraccion("Minas Tirith", "Paisaje", 30, 2.5, 25);
		Atraccion comarca = new Atraccion("La Comarca", "Paisaje", 20, 1.5, 24);
		Atraccion extra = new Atraccion("La Extra", "Paisaje", 10, 1.5, 24);

		List<Atraccion> atraccionesPaisaje = new ArrayList<Atraccion>();
		atraccionesPaisaje.add(minas);
		atraccionesPaisaje.add(comarca);

		Promocion descuentoAbsoluto = new Absoluta(300);
		Paquete vistas = new Paquete("Vistas", "Paisaje", atraccionesPaisaje, descuentoAbsoluto);

		Visitante carlos = new Visitante("Carlos", "Paisaje", 310, 5.5);

		carlos.llevarPaquete(vistas);
		carlos.llevarAtraccion(extra);

		Integer espectedFinalMonedas = 0;
		assertEquals(espectedFinalMonedas, carlos.getPresupuesto());

		Integer espectedGasto = 310;
		assertEquals(espectedGasto, carlos.getGasto());

		Double espectedFinalTiempo = 0.0;
		assertEquals(espectedFinalTiempo, carlos.getTiempoDisp());
	}
	
}