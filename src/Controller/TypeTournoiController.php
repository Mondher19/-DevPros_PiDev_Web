<?php

namespace App\Controller;

use App\Entity\TypeTournoi;
use App\Form\TypeTournoiType;
use App\Repository\TypeTournoiRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/type/tournoi")
 */
class TypeTournoiController extends AbstractController
{
    /**
     * @Route("/", name="type_tournoi_index", methods={"GET"})
     */
    public function index(TypeTournoiRepository $typeTournoiRepository): Response
    {
        return $this->render('type_tournoi/index.html.twig', [
            'type_tournois' => $typeTournoiRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="type_tournoi_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $typeTournoi = new TypeTournoi();
        $form = $this->createForm(TypeTournoiType::class, $typeTournoi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($typeTournoi);
            $entityManager->flush();

            return $this->redirectToRoute('type_tournoi_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('type_tournoi/new.html.twig', [
            'type_tournoi' => $typeTournoi,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{id}/edit", name="type_tournoi_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, TypeTournoi $typeTournoi, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(TypeTournoiType::class, $typeTournoi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('type_tournoi_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('type_tournoi/edit.html.twig', [
            'type_tournoi' => $typeTournoi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="type_tournoi_delete", methods={"POST"})
     */
    public function delete(Request $request, TypeTournoi $typeTournoi, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$typeTournoi->getId(), $request->request->get('_token'))) {
            $entityManager->remove($typeTournoi);
            $entityManager->flush();
        }

        return $this->redirectToRoute('type_tournoi_index', [], Response::HTTP_SEE_OTHER);
    }
}
