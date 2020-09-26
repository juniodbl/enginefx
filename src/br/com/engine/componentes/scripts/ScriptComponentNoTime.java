package br.com.engine.componentes.scripts;

import br.com.engine.componentes.SimpleComponent;

public class ScriptComponentNoTime extends SimpleComponent 
{
	private SimpleScriptNoTime scnt;
	
	public ScriptComponentNoTime( SimpleScriptNoTime sc )  
	{
		this.scnt = sc;
	}
	
	@Override
	public void update(long time)
	{
		scnt.update();
	}

	@Override
	public void setup( )
	{
	}

	@Override
	public void draw( )
	{
	}
}