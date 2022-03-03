<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Form\CommandeType;
use App\Form\CommandeUpType;
use App\Repository\CommandeRepository;
use App\Repository\EquipementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;

/**
 * @Route("/commande")
 */
class CommandeController extends AbstractController
{
    /**
     * @Route("/listCommande", name="commande_index", methods={"GET"})
     */
    public function index(CommandeRepository $commandeRepository): Response
    {
        return $this->render('commande/index.html.twig', [
            'commandes' => $commandeRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="commande_new", methods={"GET","POST"})
     */
    public function new(Request $request,SessionInterface $session,EquipementRepository $gamesRepository): Response
    {
        $commande = new Commande();
        $form = $this->createForm(CommandeType::class, $commande);
        $form->handleRequest($request);
        $panier = $session->get('panier', []);
        $panierwithData = [];

        $total = 0;
        Foreach ($panier as $id => $quantity) {
            $product = $gamesRepository->find($id);
            $panierwithData[] = [


                'product' => $gamesRepository->find($id),
                'quantity' => $quantity
            ];
            $total += $product->getPrix() * $quantity;
        }
        $Quantite=[];
        if ($form->isSubmitted() && $form->isValid()) {
            for ($i = 0; $i < count($panierwithData ); $i++) {

                $commande->addListP($panierwithData [$i]['product']);
                $Quantite[$i]=($panierwithData [$i]['quantity']);

            }

            $this->addFlash('success', 'votre commande a été Ajouter !!');

            $commande->Quantite=$Quantite;

            $commande->setTotalcost($total);

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($commande);
            $entityManager->flush();

            return $this->redirectToRoute('commande_index');
        }




        return $this->render('commande/new.html.twig',
            ['commande' => $commande,'panierwithData' => $panierwithData,'total'=>$total,'form'=>$form->createView()]);
    }


    /**
     * @Route("/{id}", name="commande_show", methods={"GET"})
     */
    public function show(Commande $commande): Response
    {
        return $this->render('commande/show.html.twig', [
            'commande' => $commande,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="commande_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Commande $commande, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(CommandeType::class, $commande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('commande_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('commande/edit.html.twig', [
            'commande' => $commande,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @param Request $request
     * @param int $id
     * @param CommandeRepository $repository
     * @return Response
     * @Route ("/modifiercommandeback/{id}",name="modifiercommandeback")
     */
    public function modify(Request $request, int $id,CommandeRepository $repository): Response
    {
        //$entityManager = $this->getDoctrine()->getManager();
        $commande=$repository->find($id);
        //$classroom = $entityManager->getRepository(Classroom::class)->find($id);
        $form = $this->createForm(CommandeUpType::class, $commande);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $entityManager = $this->getDoctrine()->getManager();

            $entityManager->flush();
            return $this->redirectToRoute('commande_index');

        }

        return $this->render("commande/modify.html.twig", [
            "fo" => $form->createView()
        ]);
    }

    /**
     * @Route("/{id}", name="commande_delete", methods={"POST"})
     */
    public function delete(Request $request, Commande $commande, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$commande->getId(), $request->request->get('_token'))) {
            $entityManager->remove($commande);
            $entityManager->flush();
        }

        return $this->redirectToRoute('commande_index', [], Response::HTTP_SEE_OTHER);
    }
}
