package spold2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

	@XmlAttribute
	public String id;

	@XmlAttribute
	public String code;

	@XmlAttribute
	public String website;

	@XmlElement
	public String name;

	@XmlElement
	public String comment;

}
