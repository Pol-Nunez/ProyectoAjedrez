	package com.example.demo.model;
	
	import java.time.LocalDate;
	import java.time.LocalTime;
	import java.util.Objects;
	
	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;
	@Document(collection = "reservas")
	public class Reserva {
		@Id
		private String id;
		private Mesa mesa;
		private LocalTime hora; // HH:MM
		private LocalDate fecha; // AAAA-MM-DD
		private Jugador jugador;
		private Partida partida; 
		
		public Reserva() {}
		
		public Reserva(Mesa mesa, String horaTexto, String fechaTexto) {
			super();
			this.mesa = mesa;
			this.hora = convertirStringAHora(horaTexto);
			this.fecha = convertirStringAFecha(fechaTexto);
		}
		
		private LocalDate convertirStringAFecha(String fechaTexto) {
			LocalDate fecha = null;
			if (fechaTexto.length() == 10 
					&& fechaTexto.charAt(4) == '-'
					&& fechaTexto.charAt(7) == '-'
			) {
				int year = Integer.parseInt(fechaTexto.substring(0, 3));
				int mes = Integer.parseInt(fechaTexto.substring(5, 6));
				int dia = Integer.parseInt(fechaTexto.substring(8, 9));
				fecha = LocalDate.of(year, mes, dia);
			}
			return fecha;
		}
		
		private LocalTime convertirStringAHora(String horaTexto) {
			LocalTime hora = null;
			if (horaTexto.length() == 5 
					&& horaTexto.charAt(2) == ':'
			) {
				int hour = Integer.parseInt(horaTexto.substring(0, 1));
				int minute = Integer.parseInt(horaTexto.substring(3, 4));
				hora = LocalTime.of(hour, minute);
			}
			return hora;
		}
		
		private boolean comprobarMesa(Mesa mesa) {
			
			
			
			boolean disponible = false;
			return disponible;
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Reserva other = (Reserva) obj;
			return Objects.equals(id, other.id);
		}
	
		public Mesa getMesa() {
			return mesa;
		}
	
		public void setMesa(Mesa mesa) {
			this.mesa = mesa;
		}
	
		public LocalTime getHora() {
			return hora;
		}
	
		public void setHora(LocalTime hora) {
			this.hora = hora;
		}
	
		public LocalDate getFecha() {
			return fecha;
		}
	
		public void setFecha(LocalDate fecha) {
			this.fecha = fecha;
		}
	
		public String getId() {
			return id;
		}
	
		public Jugador getJugador() {
			return jugador;
		}
	
		public Partida getPartida() {
			return partida;
		}
	
		public void setJugador(Jugador jugador) {
			this.jugador = jugador;
		}
	
		public void setPartida(Partida partida) {
			this.partida = partida;
		}
		
		
	}