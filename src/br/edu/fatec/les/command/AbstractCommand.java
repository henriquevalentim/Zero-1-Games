package br.edu.fatec.les.command;

import br.edu.fatec.les.fachada.Fachada;
import br.edu.fatec.les.fachada.IFachada;

public abstract class AbstractCommand implements ICommand{
	
	protected IFachada fachada = new Fachada();

}
