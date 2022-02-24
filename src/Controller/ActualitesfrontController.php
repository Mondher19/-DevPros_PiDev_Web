<?php

namespace App\Controller;

use App\Entity\Actualites;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ActualitesfrontController extends AbstractController
{
    /**
     * @Route("/actualitesfront", name="actualitesfront")
     */
    public function index(): Response
    {
        return $this->render('actualitesfront/index.html.twig', [
            'controller_name' => 'ActualitesfrontController',
        ]);
    }
    /**
     * @Route("/listactf",name="listactualitesf")
     */
    public function list()
    {
        $act=$this->getDoctrine()->getRepository(Actualites::class)->findAll();
        return $this->render("actualitesfront/index.html.twig",array('actualite'=>$act));

    }
}
