package com.example.testebis2bis.api;

import com.example.testebis2bis.entity.Produto;
import com.example.testebis2bis.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produto")
public class ProdutoApi {

    @Inject
    private ProdutoService produtoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllProduto() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(produtoService.listarProdutos())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/filtro")
    public Response filterProduto(@QueryParam("tecnologias") String tecnologias, @QueryParam("mercadoAlvo") String mercadoAlvo) {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(produtoService.filterProduto(tecnologias, mercadoAlvo))
                .build();
    }
}