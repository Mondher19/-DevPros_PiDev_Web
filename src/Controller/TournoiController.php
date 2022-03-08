<?php

namespace App\Controller;

use App\Entity\Tournoi;
use App\Form\TournoiType;
use App\Repository\TournoiRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

use Dompdf\Dompdf;
use Dompdf\Options;

/**
 * @Route("/tournoi")
 */
class TournoiController extends AbstractController
{
    /**
     * @Route("/", name="tournoi_index", methods={"GET"})
     */
    public function index(TournoiRepository $tournoiRepository,PaginatorInterface $paginator , Request $request): Response
    {

        $tournois = $paginator->paginate(
        // Doctrine Query, not results
            $tournoiRepository->findAll(),
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            2
        );
        return $this->render('tournoi/index.html.twig', [
            'tournois' => $tournois ,
        ]);
    }

    /**
     * @Route("/new", name="tournoi_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $tournoi = new Tournoi();
        $form = $this->createForm(TournoiType::class, $tournoi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($tournoi);
            $entityManager->flush();

            return $this->redirectToRoute('tournoi_index', [], Response::HTTP_SEE_OTHER);
        }
        $this->addFlash('sucess', 'un tournoi est ajoute');
        return $this->render('tournoi/new.html.twig', [
            'tournoi' => $tournoi,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{id_tour}/edit", name="tournoi_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Tournoi $tournoi, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(TournoiType::class, $tournoi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('tournoi_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('tournoi/edit.html.twig', [
            'tournoi' => $tournoi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id_tour}", name="tournoi_delete", methods={"POST"})
     */
    public function delete(Request $request, Tournoi $tournoi, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$tournoi->getIdTour(), $request->request->get('_token'))) {
            $entityManager->remove($tournoi);
            $entityManager->flush();
        }
        $this->addFlash('clear', 'une commande est supprimée');
        return $this->redirectToRoute('tournoi_index', [], Response::HTTP_SEE_OTHER);
    }


    /**
     * @Route("/admin/listtour", name="tournoi_list", methods={"GET"})
     * @noinspection PhpInconsistentReturnPointsInspection
     */
    public  function  listtour (TournoiRepository $tournoiRepository):Response{


        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        $dompdf = new Dompdf($pdfOptions);

        $tournois= $tournoiRepository->findAll();


        $html = $this->renderView('tournoi/listetour.html.twig',['tournois'=>$tournois,
        ]);


        $dompdf->loadHtml($html);


        $dompdf->setPaper('A4', 'portrait');


        // Rendre le HTML au format PDF
        $dompdf->render();

        // Sortie du PDF généré dans le navigateur (téléchargement forcé)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);

    }


    /**
     * @Route("/admin/sortnbr_joueur", name="sortnbr_joueur")
     */
    public function trinbr_joueur(): Response{
        $tournoi = $this->getDoctrine()->getRepository(Tournoi::class)->sortNbr_joueur();
        return $this->render('tournoi/Sortednbr_joueur.html.twig', [
            'controller_name' => 'TournoiController',
            'nbr_joueursorted' => $tournoi,
        ]);
    }



    /**
     * @Route("/Stattournoi", name="Stattournoi")
     */
    public function StatTournoi(): Response
    {

        $tournois = $this->getDoctrine()->getRepository(Tournoi::class)->findAll();
        $nbr_joueur = [];
        $nom_tour = [];
        foreach ($tournois as $tournoi) {
            $nbr_joueur[] = $tournoi->getNbrJoueur();
            $nom_tour [] = $tournoi->getNomTour();
        }
        return $this->render('tournoi/stattournoi.html.twig', [
            'nbr_joueur' => json_encode($nbr_joueur),
            'nom_tour' => json_encode($nom_tour)
        ]);
    }



    /**
     * @Route("/search", name="search_tournoi", requirements={"id_tour":"\d+"})
     */
    public function searchT(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Tournoi::class);
        $requestString = $request->get('searchValue');
        $tournoi = $repository->findT($requestString);
        $jsonContent = $Normalizer->normalize($tournoi, 'json',[]);

        return new Response(json_encode($jsonContent));
    }

    }
