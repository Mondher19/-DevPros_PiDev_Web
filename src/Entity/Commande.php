<?php

namespace App\Entity;

use App\Repository\CommandeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * @ORM\Entity(repositoryClass=CommandeRepository::class)
 */
class Commande
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="nom is required")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="prenom is required")
     */
    private $prenom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="adresse is required")
     */
    private $adresse;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="numtelephone is required")
     */
    private $numtelephone;

    /**
     * @ORM\Column(type="integer")
     */
    private $totalcost;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="email is required")
     */
    private $email;


    /**
     * @ORM\Column(type="array" , nullable=true)
     */
    public $Quantite=[];

    /**
     * @ORM\ManyToMany(targetEntity=Equipement::class, inversedBy="commandes")
     */
    private $listP;

    public function __construct()
    {
        $this->listP = new ArrayCollection();
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getNumtelephone(): ?int
    {
        return $this->numtelephone;
    }

    public function setNumtelephone(int $numtelephone): self
    {
        $this->numtelephone = $numtelephone;

        return $this;
    }

    public function getTotalcost(): ?int
    {
        return $this->totalcost;
    }

    public function setTotalcost(int $totalcost): self
    {
        $this->totalcost = $totalcost;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getProduit(): ?string
    {
        return $this->produit;
    }

    public function setProduit(string $produit): self
    {
        $this->produit = $produit;

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->Quantite;
    }

    public function setQuantite(int $Quantite): self
    {
        $this->Quantite = $Quantite;

        return $this;
    }

    /**
     * @return Collection<int, Equipement>
     */
    public function getListP(): Collection
    {
        return $this->listP;
    }

    public function addListP(Equipement $listP): self
    {
        if (!$this->listP->contains($listP)) {
            $this->listP[] = $listP;
        }

        return $this;
    }

    public function removeListP(Equipement $listP): self
    {
        $this->listP->removeElement($listP);

        return $this;
    }

}
