package mps.core.fertigung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public enum VorgangArtTyp {
	
	BEREITSTELLUNG, MONTAGE;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;
}