<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220302121527 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande ADD adresse VARCHAR(255) NOT NULL, ADD numtelephone INT NOT NULL, ADD totalcost INT NOT NULL, ADD email VARCHAR(255) NOT NULL, ADD produit VARCHAR(600) NOT NULL, ADD quantite INT NOT NULL, DROP prod, DROP price, DROP totprice, DROP adress, DROP num_tel');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande ADD prod VARCHAR(255) NOT NULL, ADD price DOUBLE PRECISION NOT NULL, ADD totprice DOUBLE PRECISION NOT NULL, ADD adress VARCHAR(255) NOT NULL, ADD num_tel VARCHAR(255) NOT NULL, DROP adresse, DROP numtelephone, DROP totalcost, DROP email, DROP produit, DROP quantite');
    }
}
