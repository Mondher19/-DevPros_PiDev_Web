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
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Validator\Constraints\Json;

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

/********************************mobile********************************************/



    /**
     * @Route("/addEmpJSON", name="typetour")
     */
    public function newTour(Request $request,NormalizerInterface  $normalizer)
    {
        $em= $this->getDoctrine()->getManager();
        $typetour= new TypeTournoi();
        $typetour->setNomType($request->get('NomType'));
        $typetour->setDescType($request->get('DescType'));
        $em->persist($typetour);
        $em->flush();
        $jsonContent =$normalizer->normalize($typetour,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }


    /**
     * @Route("/afficheTour", name="apiafficheTour")
     */
    public function AfficheApi(NormalizerInterface $Normalizer) {
        $agence=$this->getDoctrine()->getRepository(TypeTournoi::class)->findAll();

        $jsonContent=$Normalizer->normalize($agence,'json',['groups'=>'post:read']);


        return new Response(json_encode($jsonContent));

    }



    /**
     * @Route("/updatetour/{id}",name="updateEmp", methods={"GET","POST"})
     *
     */
    public function update(Request $request,$id,NormalizerInterface  $normalizer)
    {
        $em= $this->getDoctrine()->getManager();
        $type= $this->getDoctrine()->getRepository(TypeTournoi::class)->find($id);

        $type->setNomType($request->get('NomType'));
        $type->setDescType($request->get('DescType'));
        $em->persist($type);
        $em->flush();
        $jsonContent =$normalizer->normalize($type,'json',['groups'=>'post:read']);
        return new Response("Information updated successfully".json_encode($jsonContent));
    }



    /**
     * @Route("/supprimerTour/{id}",name="supprimertour")
     */
    public function supprimer(Request $request, NormalizerInterface  $normalizer, $id)

    {
        $em = $this -> getDoctrine() -> getManager();
        $tour = $em -> getRepository(TypeTournoi::class) -> find($id);
        $em -> remove($tour);
        $em -> flush();
        $jsonContent = $normalizer -> normalize($tour, 'json', ['groups' => 'post:read']);
        return new Response("Information deleted successfully" . json_encode($jsonContent));


    }



}
