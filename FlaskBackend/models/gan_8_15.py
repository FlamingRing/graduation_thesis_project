import torch.nn as nn
import torch


class generator(nn.Module):
	def __init__(self, input_size=128, attribute_count=8):
		super(generator, self).__init__()
		self.input_size = input_size
		self.num_channels = 3
		self.noise_dim = 100
		self.embed_dim = attribute_count
		self.latent_dim = self.noise_dim + self.embed_dim

		self.fc = nn.Sequential(
			# concat the tensors(vectors) of noise and classes
			nn.Linear(self.latent_dim, 1024),
			nn.BatchNorm1d(1024),
			nn.ReLU(),
			nn.Linear(1024, 128 * (self.input_size // 4) * (self.input_size // 4)),
			nn.BatchNorm1d(128 * (self.input_size // 4) * (self.input_size // 4)),
			nn.ReLU(),
		)
		self.deconv = nn.Sequential(
			nn.ConvTranspose2d(128, 64, 4, 2, 1),
			nn.BatchNorm2d(64),
			nn.ReLU(),
			nn.ConvTranspose2d(64, self.num_channels, 4, 2, 1),
			nn.Tanh(),
		)

	def forward(self, embed_vector, z):
		latent_vector = torch.cat([embed_vector, z], 1)
		output = self.fc(latent_vector)
		output = output.view(-1, 128, self.input_size // 4, self.input_size // 4)
		output = self.deconv(output)
		return output
