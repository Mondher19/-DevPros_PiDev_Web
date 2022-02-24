<?php

namespace App\Controller;

use App\Entity\Actualites;
use App\Entity\Categorie;
use App\Form\ActualitesType;
use App\Form\CategorieType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ActualitesController extends AbstractController
{
    /**
     * @Route("/actualites", name="actualites")
     */
    public function index(): Response
    {
        return $this->render('actualites/index.html.twig', [
            'controller_name' => 'ActualitesController',
        ]);
    }
    /**
     * @Route("/listact",name="listactualites")
     */
    public function list()
    {
        $act=$this->getDoctrine()->getRepository(Actualites::class)->findAll();
        return $this->render("actualites/index.html.twig",array('actualites'=>$act));

    }
    /**
     * @Route("/addact", name="addactualites")
     */
    function addactualites(Request $request)
    {
        $actualite = new Actualites();
        $formact = $this->createForm(ActualitesType::class, $actualite);
        $formact->handleRequest($request);
        if ($formact->isSubmitted() ) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($actualite);
            $em->flush();
        }
        return $this->render("actualites/actualites.html.twig", array('form' => $formact->createView()));
    }

    /**
     * @Route("/updateact/{id}",name="updateact")
     */
    public function updateactualite(Request $request, $id)
    {
        $actualite = $this->getDoctrine()->getRepository(Actualites::class)->find($id);
        $formact = $this->createForm(ActualitesType::class, $actualite);
        $formact->handleRequest($request);
        if ($formact->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();

        }
        return $this->render("actualites/actualites.html.twig", array("form" => $formact->createView()));
    }
    /**
     * @Route("/deleteact/{id}",name="deleteact")
     */
    public function deleteact($id)
    {
        $actualite = $this->getDoctrine()->getRepository(Actualites::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($actualite);
        $em->flush();
        return $this->redirectToRoute("addactualites");

    }
}
