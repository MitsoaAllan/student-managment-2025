package dao;

import db.DataSource;
import entity.Criteria;
import entity.Sex;
import entity.Student;
import jdk.jshell.spi.ExecutionControl;

import javax.management.RuntimeErrorException;
import java.sql.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class StudentCRUDOperations implements CRUDOperations<Student> {
    private final DataSource datasource = new DataSource();

//    Mandeha tsara
    @Override
    public List<Student> findAll(int page, int size) {
        ArrayList<Student> students = new ArrayList<>();
        String sql = """
                SELECT * FROM student WHERE 1=1 LIMIT ? OFFSET ?
                """;
        try(Connection conn =datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setInt(1,size);
                ps.setInt(2,(page-1)*size);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setSex(Sex.valueOf(rs.getString("sex")));
                    student.setBirthdate(rs.getDate("birthdate").toLocalDate());
                    students.add(student);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return students;
    }
//    Mandeha tsara
    @Override
    public Student findById(int id) {
        Student student = new Student();
        try(Connection con = datasource.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from student where id = ?"))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(Sex.valueOf(rs.getString("sex")));
                student.setBirthdate(rs.getDate("birthdate").toLocalDate());
            }
            return student;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    //Mandeha tsara
    @Override
    public List<Student> saveAll(List<Student> entityList){
        entityList.forEach(student -> {
            try(Connection con = datasource.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO student (name,sex,birthdate) VALUES (?,?,?)"))
            {
                ps.setString(1, student.getName());
                ps.setString(2,student.getSex().toString());
                ps.setDate(3, Date.valueOf(student.getBirthdate()));
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return entityList;
    }

//    Mandeha tsara
    @Override
    public Student update(int id,Student entity) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE student SET name=?, sex=?, birthdate=? WHERE id=?"))
        {
            ps.setString(1, entity.getName());
            ps.setString(2,entity.getSex().toString());
            ps.setDate(3, Date.valueOf(entity.getBirthdate()));
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

//    Mandeha tsara
    @Override
    public void delete(int id) {
        try(Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM student WHERE id=?"))
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findByCriteria(Criteria criteria){
        ArrayList<Student> students = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE 1=1");
        try(Connection conn =datasource.getConnection();
            Statement st = conn.createStatement()){
                switch (criteria.getColumn()){
                    case "name":
                        sql.append(" AND name LIKE ('%");
                        sql.append(criteria.getValue());
                        sql.append("%')");
                        break;
                    case "birthdate":
                        sql.append(" AND birthdate= ");
                        sql.append(criteria.getValue());
                        break;
                    case "sex":
                        sql.append("AND sex=");
                        sql.append(criteria.getValue());
                        break;
                    default:
                        throw new AssertionError("Unsupported criteria column");
                }
                ResultSet rs = st.executeQuery(sql.toString());
                while (rs.next()){
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setSex(Sex.valueOf(rs.getString("sex")));
                    student.setBirthdate(rs.getDate("birthdate").toLocalDate());
                    students.add(student);
                }
                return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
