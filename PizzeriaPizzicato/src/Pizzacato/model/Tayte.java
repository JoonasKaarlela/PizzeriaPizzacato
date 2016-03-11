package Pizzacato.model;

public class Tayte {
		
		private String tayte_id;
		private String ravintosisalto;
		

public Tayte(String tayte_id, String ravintosisalto) {
	super();
	this.tayte_id = tayte_id;
	this.ravintosisalto = ravintosisalto;
}

public Tayte(){
	tayte_id = "";
	ravintosisalto ="";
}

public String getTayte_id() {
	return tayte_id;
}

public void setTayte_id(String tayte_id) {
	this.tayte_id = tayte_id;
}

public String getRavintosisalto() {
	return ravintosisalto;
}

public void setRavintosisalto(String ravintosisalto) {
	this.ravintosisalto = ravintosisalto;
}

@Override
public String toString() {
	return "Tayte [tayte_id=" + tayte_id + ", ravintosisalto=" + ravintosisalto
			+ "]";
}

}


