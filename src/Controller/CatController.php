<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Equipement;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CatController extends AbstractController
{
    /**
     * @Route("/cat", name="cat")
     */
    public function index(): Response
    {
        return $this->render('cat/index.html.twig', [
            'controller_name' => 'CatController',
        ]);
    }
    /**
     * @Route("/listcat",name="listcat")
     */
    public function list()
    {
        $cat = $this->getDoctrine()->getRepository(Categorie::class)->findAll();
        return $this->render("cat/list_cat.html.twig",array('tabclass'=>$cat));
    }
}
