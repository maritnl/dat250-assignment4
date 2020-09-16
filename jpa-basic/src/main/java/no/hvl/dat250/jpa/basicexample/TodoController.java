package no.hvl.dat250.jpa.basicexample;


import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class TodoController {

    static Todo todo = null;

    public TodoController(final TodoDAO dao) {
        after((req, res) -> {
            res.type("application/json");
        });

        get("/todos", (req, res) -> dao.read());

        get("/todos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Todo todo = dao.find(id);
            return todo;

        });

        todo = new Todo();

        post("/todos", (req,res) -> {

            Gson gson = new Gson();

            todo = gson.fromJson(req.body(), Todo.class);

            return dao.create(todo);

        });

        put("/todos", (req,res) -> {

            Gson gson = new Gson();

            todo = gson.fromJson(req.body(), Todo.class);

            return dao.update(todo);

        });

        delete("/todos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Todo todo = dao.delete(id);
            return todo;

        });


    }
}

