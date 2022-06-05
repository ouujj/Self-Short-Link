package  com.example.SelfShortLink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.SelfShortLink.Model.Link;

@RepositoryRestResource
public interface LinkRepository extends  JpaRepository<Link, String>{
    Link findByShortLink(String ShortLink);
}
