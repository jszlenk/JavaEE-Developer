package pl.codeme.jeeb.e2.bank.backend.db;

import java.util.List;

import javax.enterprise.context.Dependent;

import pl.codeme.jeeb.e2.bank.backend.model.Model;

@DataBase
@Dependent
public interface ManagerDB {

    public static QueryParameter createQueryParameter(String name, Object value) {
        return new QueryParameter(name, value);
    }

    public <T extends Model> T findById(Integer id, Class<T> clazz);

    public <T extends Model> T findByField(String fieldName, Object value, Class<T> clazz);

    public <T extends Model> T persist(T model);

    public <T extends Model> void remove(T model);

    public int executeNamed(String name, QueryParameter... params);

    public <T> T executeNamedSingle(String name, Class<T> model, QueryParameter... params);

    public <T> List<T> executeNamed(String name, Class<T> model, QueryParameter... params);

    public static class QueryParameter {

        private String name;
        private Object value;

        private QueryParameter(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + "[ " + name + " -> " + value + " ]";
        }

    }
}
