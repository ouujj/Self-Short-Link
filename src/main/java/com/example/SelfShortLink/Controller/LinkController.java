package com.example.SelfShortLink.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.SelfShortLink.Model.Link;
import com.example.SelfShortLink.Sevice.LinkEnAndDe;
import com.example.SelfShortLink.Repository.LinkRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@CrossOrigin(origins =  "*")
public class LinkController {
    public static final String domainName = "http://localhost:8080/";

	@Autowired
	private LinkRepository linkRepository;


	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public ResponseEntity<Object> hello()  {
		return new ResponseEntity<Object>("Hello", HttpStatus.OK);
	}


	@RequestMapping(value="/", method=RequestMethod.POST)
	public String getShortenUrl(@RequestBody Map<String, Object> body)  {

        Link newLink = new Link(body.get("url").toString());
		linkRepository.save(newLink);

		System.out.println("\nfulllink: " + newLink.getOriginalLink());
		System.out.println("shortlinkKey: " +newLink.getShortLink()+"\n");

		return domainName + newLink.getShortLink();
	}

	@RequestMapping(value="/{shortLink}", method=RequestMethod.GET)
	public void getFullUrl(HttpServletResponse response, @PathVariable("shortLink") String shortLink) throws IOException {
        Link fullLink = linkRepository.findByShortLink(shortLink);
	    //Link fullLink = null;
		response.sendRedirect(fullLink.getOriginalLink());
	}

	@RequestMapping(value="/AES", method=RequestMethod.POST)
	public String getShortenUrlByAES(@RequestBody Map<String, Object> body)  {

        Link newLink = new Link();

		String shortlink = LinkEnAndDe.enCrypLink(body.get("url").toString());
		//linkRepository.save(newLink);
		newLink.setOriginalLink(body.get("url").toString());
		newLink.setShortLink(shortlink);

		System.out.println("\nfulllink: " + newLink.getOriginalLink());
		System.out.println("shortlinkKey: " +newLink.getShortLink()+"\n");

		return domainName + newLink.getShortLink();
	}

	@RequestMapping(value="/AES/{shortLink}", method=RequestMethod.GET)
	public void getFullUrlByAES(HttpServletResponse response, @PathVariable("shortLink") String shortLink) throws IOException {

	    //Link fullLink = null;
		String fullLink = LinkEnAndDe.edCrypLink(shortLink);

		response.sendRedirect(fullLink);
	}

}
