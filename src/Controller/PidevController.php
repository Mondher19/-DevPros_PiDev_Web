<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PidevController extends AbstractController
{
    /**
     * @Route("/pidev", name="pidev")
     */
    public function index(): Response
    {
        return $this->render('/base-front.html.twig', [
            'controller_name' => 'PidevController',
        ]);
    }
}
