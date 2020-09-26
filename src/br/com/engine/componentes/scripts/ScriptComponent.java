package br.com.engine.componentes.scripts;

import br.com.engine.componentes.SimpleComponent;

public class ScriptComponent extends SimpleComponent 
{
	private SimpleScript sc;
	
	public ScriptComponent(SimpleScript sc) {
		this.sc = sc;
	}
	
	@Override
	public void setup() {
	}

	@Override
	public void update(long time) {
		sc.update(time);
	}

	@Override
	public void draw() {
	}
}
