package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: monkey
 * @date: 2018/6/10 11:06
 */
@Repository
public class ContactsRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Contact> findAll(){
        return jdbc.query("select id, firstName, lastName, phoneNumber, emailAddress from contacts order by lastName",
                new RowMapper<Contact>() {
                    @Override
                    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Contact contact=new Contact();
                        contact.setId(rs.getLong("id"));
                        contact.setFirstName(rs.getString("firstName"));
                        contact.setLastName(rs.getString("lastName"));
                        contact.setPhoneNumber(rs.getString("phoneNumber"));
                        contact.setEmailAddress(rs.getString("emailAddress"));
                        return contact;
                    }
                });
    }

    public void save(Contact contact){
        jdbc.update("insert into contacts "+
        "(firstName, lastName, phoneNumber, emailAddress) "+
        "VALUES (?,?,?,?)",
                contact.getFirstName(),contact.getLastName(),contact.getPhoneNumber(),contact.getEmailAddress());
    }

}
